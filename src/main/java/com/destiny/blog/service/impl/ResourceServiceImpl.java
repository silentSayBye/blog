package com.destiny.blog.service.impl;

import com.destiny.blog.dao.ResourceRepository;
import com.destiny.blog.domain.pojo.Resource;
import com.destiny.blog.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Date 2020/2/15 17:26
 * @Version 1.0
 **/
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    public List<Resource> findAllResources() {
        return resourceRepository.findAllByState(1);
    }
}
