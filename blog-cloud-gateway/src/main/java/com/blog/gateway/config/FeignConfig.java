package com.blog.gateway.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Date 2020/3/3 20:51
 * @Version 1.0
 **/

@Configuration
public class FeignConfig {

    @Bean
    Request.Options feignOptions(){
        return new Request.Options(60 * 1000 * 1000,60 * 1000 * 1000);
    }

//    @Bean
//    Retryer feignRetry(){
//        return Retryer.NEVER_RETRY;
//    }

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
