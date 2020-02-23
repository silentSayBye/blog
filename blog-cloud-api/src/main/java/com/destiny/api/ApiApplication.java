package com.destiny.api;

import com.blog.security.metadata.CustomSecurityMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan({"com.blog.security","com.destiny.api"})
//@EnableJpaAuditing
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args)

//        initRequestMap 方法放到gateway中
                .getBean(CustomSecurityMetadata.class)
                .initRequestMap();
    }

}
