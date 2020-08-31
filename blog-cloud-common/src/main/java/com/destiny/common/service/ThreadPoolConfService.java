package com.destiny.common.service;

import com.destiny.common.domain.pojo.ThreadPoolConf;

import java.util.List;

public interface ThreadPoolConfService {

    List<ThreadPoolConf> findThreadPoolConfByTaskCode(String taskCode);

}
