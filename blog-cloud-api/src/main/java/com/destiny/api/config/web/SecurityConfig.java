package com.destiny.api.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Date 2020/3/14 22:56
 * @Version 1.0
 * 关闭 blog-cloud-api的spring security认证，由gateway统一认证
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                // csrf 不关闭会导致请求 403
                .csrf()
                .disable();
    }
}
