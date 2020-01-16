package com.kq.product.client.controller;


import com.kq.docker.dto.DtoResult;
import com.kq.docker.dto.ProductDto;
import com.kq.docker.util.Constants;
import com.kq.product.client.loadblance.RandomLoadBlance;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@Slf4j
@RequestMapping(value="/product/client")
public class ProductClientController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RandomLoadBlance randomLoadBlance;

    private AtomicInteger atomicInteger = new AtomicInteger();

    @RequestMapping(value="/add")
    public DtoResult add(ProductDto e) {

        log.debug("client-添加产品接收参数={}",e);
        DtoResult result = new DtoResult();

        String server = randomLoadBlance.getHttpServer();
        if(server==null) {
            result.setCode("10135000");
        }else {
            int curIndex = atomicInteger.getAndIncrement();
            String url = server + Constants.CONTEXT.PRODUCT_SERVER_CONTEXT_PATH+"/product/add?id="+curIndex+"&name="+ RandomStringUtils.randomAlphabetic(6);
            log.info("Product-Service add Url : {}",url);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
            result.setResult(responseEntity.getBody());
        }

        return result;
    }

    @RequestMapping(value="/list")
    public DtoResult list() {
        log.debug("client-请求获取产品列表");
        DtoResult result = new DtoResult();

        String server = randomLoadBlance.getHttpServer();
        if(server==null) {
            result.setCode("10135000");
        } else {
            String url = server + Constants.CONTEXT.PRODUCT_SERVER_CONTEXT_PATH+"/product/list";
            log.info("Product-Service add Url : {}",url);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);
            result.setResult(responseEntity.getBody());
        }

        return result;

    }


}
