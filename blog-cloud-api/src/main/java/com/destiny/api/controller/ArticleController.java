package com.destiny.api.controller;

import com.destiny.api.domain.vo.ArticleVO;
import com.destiny.api.domain.vo.Response;
import com.destiny.api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Date 2020/8/29 19:58
 * @Version 1.0
 **/

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @PostMapping
    public Response insertArticle(@Valid ArticleVO articleVO){
        articleService.insertArticle(articleVO);
        return Response.success(null);
    }
}
