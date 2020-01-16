package com.kq.product.config;

import com.kq.docker.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class ServiceRegister {

    public static final String PRODUCT_SERVICE_PREFIX = "/product_service";

    @Autowired
    private CuratorComponent curatorComponent;

    @Value("${server.port}")
    private int port;

    @PostConstruct
    public void init(){

        try {
            String ip = IPUtil.getLocalHost();
            String path = ip+":"+port;
            log.info("Product-Service注册服务地址={}",path);
            curatorComponent.createPath(PRODUCT_SERVICE_PREFIX+"/"+path, path, true);
        }catch (Exception e){
            log.error("Product-Service服务注册失败！",e);
        }

    }




}
