package com.destiny.api.dao.impl;

import com.destiny.api.dao.BaseHibernate;
import com.destiny.api.dao.custom.ArticleRepositoryCustom;
import com.destiny.api.domain.pojo.Article;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @Date 2020/4/1 22:20
 * @Version 1.0
 **/
public class ArticleRepositoryImpl extends BaseHibernate<Article> implements ArticleRepositoryCustom {

    // 相比select * from d_article where status = 1 limit 10000,10； 在偏离量大的时候，减少回表查询
    // TODO 两条sql性能待测试
    @Override
    public List<Article> findArticleListByCondition(Integer status, Integer offset, Integer limit) {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> condition = Maps.newHashMap();
        sql.append("select * from d_article a1 inner join (select id from d_article where  ");
        Preconditions.checkArgument(status != null, "article status cannot be null.");
        sql.append(" status = :status ");
        condition.put("status", status);
        if (offset != null) {
            sql.append(" limit :offset ");
            condition.put("offset", offset);
            if (limit != null) {
                sql.append(" , :limit");
                condition.put("limit", limit);
            }
        }
        sql.append(" ) a2 on a1.id = a2.id ");
        return findAllObjectByCondition(sql.toString(),condition);
    }
}
