package com.destiny.api;

import com.destiny.api.config.security.CustomSecurityMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
//@EnableJpaAuditing
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args)
                .getBean(CustomSecurityMetadata.class)
                .initRequestMap();
    }

}
