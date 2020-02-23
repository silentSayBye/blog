package com.blog.security.service;

import com.blog.security.config.FeignConfiguration;
import com.blog.security.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "blog-cloud-api",fallback = ResourceRemoteServiceFallBack.class,configuration = FeignConfiguration.class)
public interface ResourceRemoteService {

    @RequestMapping(value = "/resource}",method = RequestMethod.GET)
    Response findAllResources();
}
