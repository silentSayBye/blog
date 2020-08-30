package com.destiny.api.service.impl;

import com.destiny.api.dao.ArticleContentRepository;
import com.destiny.api.dao.ArticleRepository;
import com.destiny.api.domain.pojo.Article;
import com.destiny.api.domain.pojo.ArticleContent;
import com.destiny.api.domain.vo.ArticleVO;
import com.destiny.api.service.ArticleService;
import com.destiny.api.util.StringUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleContentRepository articleContentRepository;

    @Override
    @Transactional
    public void insertArticle(ArticleVO articleVO) {
        Article article = new Article();
        BeanUtils.copyProperties(articleVO,article);
        Article savedArticle = articleRepository.save(article);
        String content = articleVO.getContent();
        List<String> splitList = StringUtil.split(content, 2000);
        List<ArticleContent>  articleContentList = Lists.newArrayList();
        for (int i = 0; i < splitList.size(); i++) {
            ArticleContent articleContent = new ArticleContent();
            articleContent.setArticleId(savedArticle.getId());
            articleContent.setOrder(i);
            articleContent.setContent(splitList.get(i));
            articleContentList.add(articleContent);
        }
        articleContentRepository.saveAll(articleContentList);
    }
}
