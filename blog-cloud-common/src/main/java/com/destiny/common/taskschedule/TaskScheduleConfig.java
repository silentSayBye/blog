package com.destiny.common.taskschedule;

import com.destiny.common.dao.TaskScheduleConfRepository;
import com.destiny.common.dao.ThreadPoolConfRepository;
import com.destiny.common.domain.pojo.TaskScheduleConf;
import com.destiny.common.domain.pojo.ThreadPoolConf;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskScheduleConfig {

    @Autowired
    private TaskScheduleConfRepository taskScheduleConfRepository;

    @Autowired
    private ThreadPoolConfRepository threadPoolConfRepository;

    public void init(String serviceName) {
        List<TaskScheduleConf> taskScheduleConfList
                = taskScheduleConfRepository.findAllByServiceName(serviceName);
        if (CollectionUtils.isEmpty(taskScheduleConfList)) {
            return;
        }
        taskScheduleConfList.stream().forEach(taskScheduleConf -> {
            String taskCode = taskScheduleConf.getTaskCode();
            List<ThreadPoolConf> threadPoolConfList = threadPoolConfRepository.findAllByTaskCode(taskCode);
            if (CollectionUtils.isNotEmpty(threadPoolConfList)) {
                threadPoolConfList.stream().forEach(threadPoolConf -> {

                });
            }
        });
    }

}
