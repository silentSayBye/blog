package com.destiny.blog.dao;

import com.destiny.blog.domain.pojo.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory,Integer> {
    /**
     * 根据类型type和状态status查询
     *
     * @return
     */
    @Query("from ArticleCategory a  where a.status = :status and a.type = :type")
    List<ArticleCategory> findByTypeAndStatus(@Param("status") Integer status, @Param("type") Integer type);

}
