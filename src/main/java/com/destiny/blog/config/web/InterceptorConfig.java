package com.destiny.blog.config.web;

import com.destiny.blog.config.interceptor.ResponseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName interceptorConfig
 * @Author Administrator
 * @Date 2019/7/2015:52
 * @Version 1.0
 **/

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private ResponseInterceptor responseInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(responseInterceptor).addPathPatterns("/**");
    }

}
