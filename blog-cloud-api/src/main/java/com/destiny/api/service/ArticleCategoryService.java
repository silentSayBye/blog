package com.destiny.api.service;

import com.destiny.api.domain.pojo.ArticleCategory;
import com.destiny.api.domain.vo.ArticleCategoryVO;

import java.util.List;

public interface ArticleCategoryService {

    List<ArticleCategory> findAllArticleCategoryByTypeAndStatus(Integer type, Integer status);

    ArticleCategory saveArticleCategory(ArticleCategoryVO articleCategoryVo);
}
