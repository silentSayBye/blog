package com.destiny.blog.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SelectService<E,PK> {

    E selectByPK(PK pk);

    List<E> selectByPks(Iterable<PK> pks);

    List<E> selectAll();

    Page<E> selectByPage(Pageable pageable);
}
