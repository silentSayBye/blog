package com.destiny.blog.service.impl;

import com.destiny.blog.dao.ArticleCategoryRepository;
import com.destiny.blog.domain.pojo.ArticleCategory;
import com.destiny.blog.domain.vo.ArticleCategoryVO;
import com.destiny.blog.exception.CustomException;
import com.destiny.blog.service.ArticleCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Autowired
    private RedisTemplate<String,? extends Object> redisTemplate;


    @Autowired
    private ArticleCategoryRepository articleCategoryRepository;

    @Override
    public List<ArticleCategory> findAllArticleCategoryByTypeAndStatus(@NotNull Integer type,@NotNull Integer status) {
        return  articleCategoryRepository.findByTypeAndStatus(status,type);
    }

    @Override
    public ArticleCategory saveArticleCategory(ArticleCategoryVO articleCategoryVo) {
        ArticleCategory category = new ArticleCategory();
        BeanUtils.copyProperties(articleCategoryVo,category);
        return articleCategoryRepository.save(category);
    }
}
