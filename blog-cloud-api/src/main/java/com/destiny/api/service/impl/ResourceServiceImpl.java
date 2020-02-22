package com.destiny.api.service.impl;

import com.destiny.api.dao.ResourceRepository;
import com.destiny.api.domain.pojo.Resource;
import com.destiny.api.service.ResourceService;
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
