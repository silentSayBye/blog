package com.destiny.security.service;

import com.blog.security.entity.Response;
import com.destiny.security.entity.Response;
import org.springframework.stereotype.Component;

/**
 * @Date 2020/2/23 0:00
 * @Version 1.0
 **/

@Component
public class UserRemoteServiceFallBack implements UserRemoteService{

    @Override
    public Response findByUsername(String username) {
        return null;
    }

    @Override
    public Response findAuthorityByUsername(String username) {
        return null;
    }
}
