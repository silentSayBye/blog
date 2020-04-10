package com.destiny.api.dao;

import com.destiny.api.dao.custom.ArticleContentRepositoryCustom;
import com.destiny.api.domain.pojo.ArticleContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleContentRepository extends ArticleContentRepositoryCustom, JpaRepository<ArticleContent,Integer> {

    @Modifying
    @Query("update ArticleContent  set state = 0 where articleId = :articleId")
    void deleteArticleContentByArticleId(@Param("articleId") Integer articleId);

    @Query("select a from ArticleContent a where a.articleId = :articleId order by a.order asc ")
    List<ArticleContent> findByArticleId(@Param("articleId") Integer articleId);
}
