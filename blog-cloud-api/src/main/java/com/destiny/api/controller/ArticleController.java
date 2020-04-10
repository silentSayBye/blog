package com.destiny.api.controller;

import com.destiny.api.domain.vo.ArticleVO;
import com.destiny.api.domain.vo.Response;
import com.destiny.api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date 2020/4/1 21:48
 * @Version 1.0
 **/

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/article/{id}")
    public Response findArticleById(@PathVariable("id") Integer articleId){
        ArticleVO articleVO = null;
        try {
            articleVO = articleService.findArticleById(articleId);
        } catch (Exception e) {
            return Response.failed(e.getMessage());
        }
        return Response.success(articleVO);
    }

    @GetMapping(value = "/article/list")
    public Response findArticleListByCondition(@RequestParam(name = "status") Integer status,
                                @RequestParam(name = "offset",required = false) Integer offset,
                                @RequestParam(name = "limit", required = false) Integer limit){
        List<ArticleVO> articleVOList;
        try {
            articleVOList = articleService.findArticleListByCondition(status, offset, limit);
        } catch (Exception e) {
            return Response.failed(e.getMessage());
        }
        return Response.success(articleVOList);
    }

    @PostMapping(value = "/article/{id}")
    public Response deleteArticleById(@PathVariable("id") Integer articleId){
        try {
            articleService.deleteArticle(articleId);
        } catch (Exception e) {
            return Response.failed(e.getMessage());
        }
        return Response.success("Delete success.");
    }

    @PostMapping(value = "/article")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createArticle(@RequestBody ArticleVO articleVO){
        try {
            articleService.saveArticle(articleVO);
        } catch (Exception e) {
            return Response.failed(e.getMessage());
        }

        return Response.success("Article save successfully.");
    }

}
