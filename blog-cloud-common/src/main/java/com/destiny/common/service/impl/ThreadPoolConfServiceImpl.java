package com.destiny.common.service.impl;

import com.destiny.common.dao.ThreadPoolConfRepository;
import com.destiny.common.domain.pojo.ThreadPoolConf;
import com.destiny.common.service.ThreadPoolConfService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThreadPoolConfServiceImpl implements ThreadPoolConfService {

    @Autowired
    private ThreadPoolConfRepository threadPoolConfRepository;

    @Override
    public List<ThreadPoolConf> findThreadPoolConfByTaskCode(String taskCode) {
        Preconditions.checkNotNull(taskCode, "taskCode is null");
        return threadPoolConfRepository.findAllByTaskCode(taskCode);
    }
}
