package com.blog.gateway;

import com.blog.security.metadata.CustomSecurityMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = {"com.blog.security"})
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication(exclude = {HttpEncodingAutoConfiguration.class})
@ComponentScan({"com.blog.gateway","com.blog.security"})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args)
                .getBean(CustomSecurityMetadata.class)
                .initRequestMap();
    }

}
