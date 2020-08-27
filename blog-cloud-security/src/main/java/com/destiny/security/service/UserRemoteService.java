package com.destiny.security.service;

import com.destiny.security.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "BLOG-CLOUD-API",fallback = UserRemoteServiceFallBack.class)
public interface UserRemoteService {

    @RequestMapping(value = "/user/{username}",method = RequestMethod.GET)
    Response findByUsername(@PathVariable("username") String username);

    @RequestMapping(value ="/user/authority/{username}", method = RequestMethod.GET)
    Response findAuthorityByUsername(@PathVariable("username") String username);
}
