package com.destiny.api.dao;

import com.destiny.api.dao.custom.ArticleRepositoryCustom;
import com.destiny.api.domain.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer>, ArticleRepositoryCustom {

    @Modifying
    @Query("update Article set status = 0 where id = :id")
    void deleteArticleById(@Param(value = "id")  Integer ArticleId);

    @Query("select t from Article t")
    List<Article> findAll();
}
