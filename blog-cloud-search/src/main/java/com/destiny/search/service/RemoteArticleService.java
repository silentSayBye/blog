package com.destiny.search.service;


import com.blog.security.entity.Response;
import com.destiny.search.service.impl.RemoteArticleServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "blog-cloud-api", url = "http://localhost:8080/", fallback = RemoteArticleServiceFallBack.class)
public interface RemoteArticleService {

    @GetMapping(value = "/article/all")
    Response findArticleList();
}
