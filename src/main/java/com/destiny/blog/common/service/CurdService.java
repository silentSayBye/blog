package com.destiny.blog.common.service;

public interface CurdService<E,PK> extends DeleteService<E,PK>,InsertService<E,PK>,SelectService<E,PK>,UpdateService<E,PK> {
}
