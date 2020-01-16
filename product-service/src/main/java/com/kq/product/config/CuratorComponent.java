package com.kq.product.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class CuratorComponent {

    @Value("${zookeeper.server.url}")
    private String zookeeperServer;

    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);

    private CuratorFramework client = null;

    public CuratorComponent(){

    }

    @PostConstruct
    public void init(){

        client = CuratorFrameworkFactory.newClient(zookeeperServer, retryPolicy);
        client.start();

        if(client==null){
            throw new RuntimeException("CuratorFramework client 初始化失败！");
        }

    }


    public void createPath(String path,String data,boolean temporary) throws Exception{

        if(temporary) {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, data.getBytes());
        }else {
            client.create().creatingParentsIfNeeded().forPath(path, data.getBytes());
        }

    }


    /**
     * 获取子路径
     * @param path
     * @return
     * @throws Exception
     */
    public List<String> getChildPaths(String path) throws Exception{

        return client.getChildren().watched().forPath(path);

    }

}
