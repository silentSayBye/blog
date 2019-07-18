package com.destiny.blog.service.impl;

import com.destiny.blog.dao.ArticleCategoryRepository;
import com.destiny.blog.domain.pojo.ArticleCategory;
import com.destiny.blog.exception.CustomException;
import com.destiny.blog.service.ArticleCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    private static Logger logger = LoggerFactory.getLogger(ArticleCategoryServiceImpl.class);

    @Autowired
    private RedisTemplate<String,? extends Object> redisTemplate;


    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    @Override
    public List<ArticleCategory> findAllArticleCategory() {
        return articleCategoryRepository.findAllArticleCategory();
    }

    @Override
    @Cacheable(key = "#category",value = "article_category")
    public ArticleCategory findArticleCategoryByName(String category) {
        logger.info("category:{}",category);
        if(StringUtils.isBlank(category)){
           throw new CustomException("category 不能为空");
        }
        ArticleCategory articleCategory = articleCategoryRepository.findArticleCategoryByName(category);
        return  articleCategory;
    }
}
