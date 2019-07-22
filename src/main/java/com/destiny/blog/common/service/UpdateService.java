package com.destiny.blog.common.service;

public interface UpdateService<E,PK> {

    E saveOrUpdate(E  e,PK pk);
}
