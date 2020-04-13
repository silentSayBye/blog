package com.destiny.search.dao;

import com.destiny.search.entity.ESArticle;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Date 2020/4/13 22:35
 * @Version 1.0
 **/
public interface ESArticleRepository extends ElasticsearchRepository<ESArticle, Long> {

}
