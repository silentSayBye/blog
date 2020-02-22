package com.destiny.api.common.service;

public interface DeleteService<E,PK> {

    int deleteByPK(PK pk);

    int deleteByPKs(Iterable<E> pks);
}
