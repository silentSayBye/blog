package com.destiny.api.service.impl;

import com.destiny.api.dao.ArticleCategoryRepository;
import com.destiny.api.domain.pojo.ArticleCategory;
import com.destiny.api.domain.vo.ArticleCategoryVO;
import com.destiny.api.service.ArticleCategoryService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Autowired
    private RedisTemplate<String,? extends Object> redisTemplate;


    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    @Override
    @Cacheable(key = "'ArticleCategory-'.concat(#type)", value = "ArticleCategory")
    public List<ArticleCategory> findAllArticleCategoryByTypeAndStatus(Integer type,Integer status) {
        Preconditions.checkArgument(type != null,"文章类型不能为空");
        Preconditions.checkArgument(status != null,"文章状态不能为空");
        return articleCategoryRepository.findByTypeAndStatus(status,type);

    }

    @Override
    public ArticleCategory saveArticleCategory(ArticleCategoryVO articleCategoryVo) {
        ArticleCategory category = new ArticleCategory();
        BeanUtils.copyProperties(articleCategoryVo,category);
        return articleCategoryRepository.save(category);
    }
}
