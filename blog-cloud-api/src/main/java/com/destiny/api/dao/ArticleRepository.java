package com.destiny.api.dao;

import com.destiny.api.domain.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {
}
