package com.destiny.search.service.impl;

import com.blog.security.entity.Response;
import com.destiny.search.service.RemoteArticleService;
import org.springframework.stereotype.Component;

/**
 * @Date 2020/4/13 22:07
 * @Version 1.0
 **/
@Component
public class RemoteArticleServiceFallBack implements RemoteArticleService {

    @Override
    public Response findArticleList() {
        return null;
    }
}
