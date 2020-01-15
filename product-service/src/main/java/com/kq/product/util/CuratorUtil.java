package com.kq.product.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.List;

public class CuratorUtil {

    /**
     * 创建节点
     * @param zookeeperServer
     * @param path
     * @param data
     * @param temporary  true:临时节点
     * @throws Exception
     */
    public static void createPath(String zookeeperServer,String path,String data,boolean temporary) throws Exception{

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperServer, retryPolicy);
        client.start();

        if(temporary) {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, data.getBytes());
        }else {
            client.create().creatingParentsIfNeeded().forPath(path, data.getBytes());
        }

    }

    public static List<String> getChildPaths(String zookeeperServer, String path) throws Exception{

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperServer, retryPolicy);
        client.start();

        return client.getChildren().watched().forPath(path);


    }


    public static String getData(String zookeeperServer, String path) {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperServer, retryPolicy);
        client.start();

        try {
            return new String(client.getData().forPath(path));
        } catch (Exception e) {
            System.out.println("更新节点数据失败, elog=" + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws Exception{

        String zookeeperServer = "192.168.5.208:2181";
        String basePath = "/mytest";

//        for(int i=0;i<5;i++) {
//            CuratorUtil.createPath(zookeeperServer, "/mytest/" +i,""+i,i%2==0);
//        }

        List<String> paths = getChildPaths(zookeeperServer,basePath);

        for(String path : paths) {
            String data = getData(zookeeperServer,basePath+"/"+path);
            System.out.println("path:="+path+" data="+data);
        }



        System.out.println("-----------------------------------");
        Thread.sleep(1000*6);
    }

}
