package com.destiny.common.taskschedule;

import com.destiny.common.dao.TaskScheduleConfRepository;
import com.destiny.common.dao.ThreadPoolConfRepository;
import com.destiny.common.domain.pojo.TaskScheduleConf;
import com.destiny.common.domain.pojo.ThreadPoolConf;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.stereotype.Component;

import java.util.List;

public class TaskScheduleConfig {

    public static void init(ApplicationContext cxt, String serviceName) {
        TaskScheduleConfRepository taskScheduleConfRepository = (TaskScheduleConfRepository) cxt.getBean("taskScheduleConfRepository");
        ThreadPoolConfRepository threadPoolConfRepository = (ThreadPoolConfRepository) cxt.getBean("threadPoolConfRepository");
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
                    Integer poolSize = threadPoolConf.getPoolSize() == null ? 10 : threadPoolConf.getPoolSize();
                    Integer waitTime = threadPoolConf.getWaitTime() == null ? 10 : threadPoolConf.getWaitTime();
                    String poolCode = threadPoolConf.getPoolCode();
                    String poolName = threadPoolConf.getPoolName();
                    if (StringUtils.isNotBlank(poolCode)) {
                        ThreadPoolService.initThreadPool(poolCode, poolName, poolSize, waitTime);
                    }
                });
            }
            String beanName = taskScheduleConf.getBeanName();
            String corn = taskScheduleConf.getCorn();
            Integer lockTime = taskScheduleConf.getLockTime();
            Boolean ifLock = taskScheduleConf.getIfLock();
            String taskName = taskScheduleConf.getTaskName();
            if (StringUtils.isNotBlank(beanName)) {
                ScheduleService.startTask(cxt, taskCode, taskName, beanName, ifLock, lockTime, corn);
            }
        });
    }

}
