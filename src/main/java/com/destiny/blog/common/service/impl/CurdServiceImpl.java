package com.destiny.blog.common.service.impl;

import com.destiny.blog.common.service.CurdService;
import com.destiny.blog.domain.base.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName CurdServiceImpl
 * @Author Administrator
 * @Date 2019/7/2222:31
 * @Version 1.0
 **/
public abstract class CurdServiceImpl<E extends BaseEntity<PK>,PK> implements CurdService<E,PK> {

    @Autowired
    private JpaRepository<E,PK> jpaRepository;

    @Override
    public int deleteByPK(PK pk) {
        Assert.notNull(pk,"pk cannot be null");
        jpaRepository.deleteById(pk);
        return 1;
    }

    @Override
    public int deleteByPKs(Iterable<E> pks) {
        Assert.notNull(pks,"pks cannot be null");
        jpaRepository.deleteInBatch(pks);
        Iterator<E> iterator = pks.iterator();
        int i = 0;
        while (iterator.hasNext()){
            i++;
        }
        return i;
    }


    @Override
    public E selectByPK(PK pk) {
        Assert.notNull(pk,"pk cannot be null");
        Optional<E> object = jpaRepository.findById(pk);
        return object.get();
    }

    @Override
    public List<E> selectByPks(Iterable<PK> pks) {
        Assert.notNull(pks,"pks cannot be null");
        return jpaRepository.findAllById(pks);
    }

    @Override
    public List<E> selectAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Page<E> selectByPage(Pageable pageable) {
        Assert.notNull(pageable,"pageable cannot be null");
        return jpaRepository.findAll(pageable);
    }

    @Override
    public E saveOrUpdate(E e, PK pk) {
        Assert.notNull(e,"object cannot be null");
        Assert.notNull(pk,"pk cannot be null");
        return jpaRepository.save(e);
    }
}
