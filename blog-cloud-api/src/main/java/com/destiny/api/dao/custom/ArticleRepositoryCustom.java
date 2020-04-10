package com.destiny.api.dao.custom;

import com.destiny.api.domain.pojo.Article;

import java.util.List;

public interface ArticleRepositoryCustom {

    List<Article> findArticleListByCondition(Integer status, Integer offset, Integer limit);
}
