package com.kq.product.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ZookeeperConfiuration {

    @Value("${zookeeper.server.url}")
    private String zookeeperServer;

    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(5000, 3);

    private CuratorFramework client = null;

    public ZookeeperConfiuration(){
        client = CuratorFrameworkFactory.newClient(zookeeperServer, retryPolicy);
        client.start();
    }

    @PostConstruct
    public void init(){

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

}
