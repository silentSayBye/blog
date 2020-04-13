package com.destiny.api.service.impl;

import com.destiny.api.dao.ArticleContentRepository;
import com.destiny.api.dao.ArticleRepository;
import com.destiny.api.domain.pojo.Article;
import com.destiny.api.domain.pojo.ArticleContent;
import com.destiny.api.domain.vo.ArticleVO;
import com.destiny.api.exception.CustomException;
import com.destiny.api.service.ArticleService;
import com.destiny.api.util.BeanUtil;
import com.destiny.api.util.StringUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleContentRepository articleContentRepository;

    @Override
    @Transactional
    public void saveArticle(ArticleVO articleVO) {
        Article article = new Article();
        BeanUtil.copyBean(article,articleVO);
        articleRepository.save(article);
        List<String> list = StringUtil.splitContent(articleVO.getContent(), 3000);
        List<ArticleContent> articleContentList = Lists.newArrayList();
        for (int i = 0; i < list.size(); i++) {
            ArticleContent articleContent = new ArticleContent();
            articleContent.setArticleId(articleVO.getArticleId());
            articleContent.setContent(list.get(i));
            articleContent.setOrder(i);
            articleContent.setState(1);
            articleContentList.add(articleContent);
        }
        articleContentRepository.saveAll(articleContentList);
    }

    @Override
    @Transactional
    public void deleteArticle(Integer articleId) {
        checkArgument(articleId);
        articleRepository.deleteArticleById(articleId);
        articleContentRepository.deleteArticleContentByArticleId(articleId);
    }

    @Override
    public ArticleVO findArticleById(Integer articleId) {
        checkArgument(articleId);
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        Article article = optionalArticle.orElseThrow(() -> new CustomException("Article is not exist."));
        return fillArticleVo(article);
    }

    @Override
    public List<ArticleVO> findArticleListByCondition(Integer status, Integer offset, Integer limit) {
        Preconditions.checkArgument(status != null, "Article status cannot be null." );
        List<Article> articleList = articleRepository.findArticleListByCondition(status, offset, limit);
        List<ArticleVO> articleVOList = Lists.newArrayList();
        articleList.forEach(article -> {
            articleVOList.add(fillArticleVo(article));
        });
        return articleVOList;
    }

    private void checkArgument(Integer var){
        Preconditions.checkArgument(var != null, "Article id cannot be null." );
    }

    private ArticleVO fillArticleVo(Article article){
        ArticleVO articleVO = new ArticleVO();
        BeanUtil.copyBean(articleVO,article);
        Preconditions.checkArgument(article.getId() != null, "Article Id cannot be null.");
        List<ArticleContent> ArticleContentList = articleContentRepository.findByArticleId(article.getId());
        List<String> contentList = ArticleContentList.stream().map(ArticleContent::getContent)
                .collect(Collectors.toList());
        String contentStr = StringUtil.mergeToString(contentList);
        articleVO.setContent(contentStr);
        return articleVO;
    }

    @Override
    public List<ArticleVO> findAll() {
        List<Article> articleList = articleRepository.findAll();
        List<ArticleVO> articleVOList = Lists.newArrayList();
        articleList.forEach(article -> {
            articleVOList.add(fillArticleVo(article));
        });
        return articleVOList;
    }
}
