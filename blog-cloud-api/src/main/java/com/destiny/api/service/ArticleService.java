package com.destiny.api.service;


import com.destiny.api.domain.vo.ArticleVO;

import java.util.List;

public interface ArticleService {

    void saveArticle(ArticleVO article);

    void deleteArticle(Integer articleId);

    ArticleVO findArticleById(Integer articleId);

    List<ArticleVO> findArticleListByCondition(Integer status, Integer offset, Integer limit);

    List<ArticleVO> findAll();
}
