package com.destiny.blog.service;

import com.destiny.blog.domain.pojo.ArticleCategory;
import com.destiny.blog.domain.vo.ArticleCategoryVO;

import java.util.List;

public interface ArticleCategoryService {

    List<ArticleCategory> findAllArticleCategoryByTypeAndStatus(Integer type, Integer status);

    ArticleCategory saveArticleCategory(ArticleCategoryVO articleCategoryVo);
}
