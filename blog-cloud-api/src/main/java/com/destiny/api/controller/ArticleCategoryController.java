package com.destiny.api.controller;

import com.destiny.api.domain.pojo.ArticleCategory;
import com.destiny.api.domain.vo.ArticleCategoryVO;
import com.destiny.api.domain.vo.Response;
import com.destiny.api.mapper.ArticleCategoryMapper;
import com.destiny.api.service.ArticleCategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Date 2020/2/14 17:15
 * @Version 1.0
 **/

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleCategoryController {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @ApiOperation("获取文章类别")
    @GetMapping("/category/{typeId}")
    public Response listArticleCategoryByType(@PathVariable("typeId") Integer typeId){
        List<ArticleCategory> listCategory = articleCategoryService.findAllArticleCategoryByTypeAndStatus(typeId, 1);
        List<ArticleCategoryVO> listCategoryVO = ArticleCategoryMapper.getInstance.entityToVOList(listCategory);
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
