package com.kq.elasticjob.config.esjob;

import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.kq.elasticjob.taskjob.simplejob.SimpleJobDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局作业配置中心
 */
@Configuration
public class JobConfig {

    @Autowired
    private ZookeeperRegistryCenter regCenter;
    @Autowired
    private LiteJobConfiguration liteJobConfiguration;
    @Autowired
    private SimpleJobDemo simpleJobDemo;

    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler() {
        return new SpringJobScheduler(simpleJobDemo, regCenter, liteJobConfiguration);
    }
}

