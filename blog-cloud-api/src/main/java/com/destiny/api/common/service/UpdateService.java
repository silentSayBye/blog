package com.destiny.api.common.service;

public interface UpdateService<E,PK> {

    E saveOrUpdate(E e, PK pk);
}
