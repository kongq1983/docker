package com.kq.product.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ActivemqProducerApplication
 *
 * @author kq
 * @date 2019-08-06
 */
@EnableScheduling
@SpringBootApplication
public class ProductClientApplication {

    protected static Logger logger = LoggerFactory.getLogger(ProductClientApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProductClientApplication.class, args);

        String[] beanNames = context.getBeanDefinitionNames();

        for(String beanName : beanNames) {
            logger.info("load beanName ={}",beanName);
        }

        logger.info("load beanNames size ={}",context.getBeanDefinitionCount());

    }

}
