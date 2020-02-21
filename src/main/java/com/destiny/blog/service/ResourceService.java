package com.destiny.blog.service;

import com.destiny.blog.domain.pojo.Resource;

import java.util.List;

/**
 * @Date 2020/2/15 17:25
 * @Version 1.0
 **/
public interface ResourceService {

    List<Resource> findAllResources();
}
