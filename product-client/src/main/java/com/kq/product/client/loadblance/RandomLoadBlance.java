package com.kq.product.client.loadblance;

import com.kq.docker.util.Constants;
import com.kq.product.client.config.CuratorComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class RandomLoadBlance {

    @Autowired
    private CuratorComponent curatorComponent;


    public String getServer() {

        try {
            List<String> servers = curatorComponent.getChildPaths(Constants.PRODUCT_SERVICE_PREFIX);

            if(!CollectionUtils.isEmpty(servers)) {
                int size = servers.size();
                Random r = new Random();
                int index = r.nextInt(size);

                String server =  servers.get(index);
                log.info("得到ProductService地址={}",server);
                return server;
            }

        }catch (Exception e){
            log.error("获取服务列表出错!",e);
        }

        return null;
    }


    public String getHttpServer() {

        String server = this.getServer();
        if(server!=null) {
            if(!server.startsWith("http://")) {
                server = "http://"+server;
            }
        }

        return server;
    }


    public static void main(String[] args) {
        Random r = new Random();
        for(int i=0;i<5;i++){
            int index = r.nextInt(5);
            System.out.println("index="+index);
        }

    }


}
