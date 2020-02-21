package com.destiny.blog.controller;

import com.destiny.blog.domain.pojo.ArticleCategory;
import com.destiny.blog.domain.vo.ArticleCategoryVO;
import com.destiny.blog.domain.vo.Response;
import com.destiny.blog.mapper.ArticleCategoryMapper;
import com.destiny.blog.service.ArticleCategoryService;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Date 2020/2/14 17:15
 * @Version 1.0
 **/

@RestController
@RequestMapping("/article")
public class ArticleCategoryController {

    @Autowired
    private ArticleCategoryService articleCategoryService;


    @ApiOperation("获取文章类别")
    @GetMapping("/category/{typeId}")
    public Response listArticleCategoryByType(@PathVariable("typeId") Integer typeId){
        List<ArticleCategoryVO> listCategoryVO = Lists.newArrayList();
        List<ArticleCategory> listCategory = articleCategoryService.findAllArticleCategoryByTypeAndStatus(typeId, 1);
        listCategoryVO = ArticleCategoryMapper.getInstance.entityToVOList(listCategory);
        return Response.success(listCategoryVO);
    }

    @ApiOperation("修改文章类别")
    @PostMapping("/category")
    public Response insertArticleCategory(@RequestBody @Valid ArticleCategoryVO articleCategoryVO){
        ArticleCategory articleCategory = articleCategoryService.saveArticleCategory(articleCategoryVO);
        if (articleCategory != null){
            return Response.success("添加成功");
        }
        return Response.success("添加失败");
    }


}
