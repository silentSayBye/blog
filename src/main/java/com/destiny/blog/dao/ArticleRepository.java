package com.destiny.blog.dao;

import com.destiny.blog.domain.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Integer> {
}
