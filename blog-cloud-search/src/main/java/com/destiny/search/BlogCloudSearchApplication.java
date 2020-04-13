package com.destiny.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = {"com.blog.security"})
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"com.blog.security","com.destiny.search"})
public class BlogCloudSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogCloudSearchApplication.class, args);
    }

}
