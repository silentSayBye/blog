package com.destiny.blog.dao;

import com.destiny.blog.domain.pojo.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory,Integer> {
    /**
     * 查询
     *
     * @return
     */
    @Query("from ArticleCategory a  where a.deleteFlag = '0'")
    List<ArticleCategory> findAllArticleCategory();

    @Query("from ArticleCategory a  where a.deleteFlag = '0' and a.category = :category")
    ArticleCategory findArticleCategoryByName(@Param("category") String category);
}
