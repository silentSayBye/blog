package com.destiny.api.dao;

import com.destiny.api.domain.pojo.ArticleContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleContentRepository extends JpaRepository<ArticleContent,Integer> {

}
