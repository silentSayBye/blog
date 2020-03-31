package com.blog.gateway.config;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Date 2020/3/6 21:40
 * @Version 1.0
 **/

@Configuration
public class SleuthConfig {
    @Bean
    public Sampler defaultSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }
}
