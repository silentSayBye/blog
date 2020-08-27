package com.destiny.security.service;

import com.blog.security.entity.Response;
import com.destiny.security.entity.Response;
import org.springframework.stereotype.Component;

/**
 * @Date 2020/2/23 0:05
 * @Version 1.0
 **/

@Component
public class ResourceRemoteServiceFallBack implements ResourceRemoteService {
    @Override
    public Response findAllResources() {
        return null;
    }
}
