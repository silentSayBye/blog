package com.destiny.api.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;

@Repository
public class BaseHibernate<T extends Serializable> {

    private static Logger logger = LoggerFactory.getLogger(BaseHibernate.class);

    @Autowired
    @PersistenceContext
    private EntityManager em;

//    private SessionFactory sessionFactory;

    private Class<T> clazz=null;

    public BaseHibernate(){
        if(getClass().getGenericSuperclass() instanceof ParameterizedType){
            if(!(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0] instanceof TypeVariable)   ){
                clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            }
        }
    }

    public void persist(Object entity){
        em.persist(entity);
    }

    public <T> T merge(T entity){
        return em.merge(entity);
    }

    public void remove(Object entity){
        em.remove(entity);
    }

    public void flush(){
        em.flush();
    }

    public void refresh(Object entity){
        em.remove(entity);
    }

    public void clear(){
        em.clear();
    }

    public boolean contains(Object entity){
        return em.contains(entity);
    }

    public List<T> findAllObjectByCondition(String sql, Map<String,Object> condition){
        Query query = em.createNativeQuery(sql, clazz);
        setParamter(query,condition);
        return query.getResultList();

    }

    public T findObjectByCondition(String sql,Map<String,Object> condition){
        Query query = em.createNativeQuery(sql,clazz);
        setParamter(query,condition);
        return (T) query.getSingleResult();
    }

    private void setParamter(Query query,Map<String,Object> condition){
        if (condition != null && condition.size()>0){
            for(Map.Entry<String,Object> map:condition.entrySet()){
                query.setParameter(map.getKey(),map.getValue());
            }
        }
    }


}
