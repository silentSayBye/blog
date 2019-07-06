package com.destiny.blog.service;

import com.destiny.blog.domain.pojo.ArticleCategory;

import java.util.List;

public interface ArticleCategoryService {

    List<ArticleCategory> findAllArticleCategory();

    ArticleCategory findArticleCategoryByName(String category);
}
