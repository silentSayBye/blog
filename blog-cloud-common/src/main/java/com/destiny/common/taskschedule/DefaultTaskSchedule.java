package com.destiny.common.taskschedule;

import com.destiny.common.domain.pojo.TaskInterface;
import com.destiny.common.domain.pojo.ThreadPoolConf;
import com.destiny.common.service.TaskInterfaceService;
import com.destiny.common.service.ThreadPoolConfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Scope("prototype")
public class DefaultTaskSchedule extends AbstractTask implements ApplicationContextAware {

    @Autowired
    private TaskInterfaceService taskInterfaceService;

    @Autowired
    private ThreadPoolConfService threadPoolConfService;

    private ApplicationContext cxt;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.cxt = applicationContext;
    }

    @Override
    public void exec() {
        List<ThreadPoolConf> threadPoolConfList =
                threadPoolConfService.findThreadPoolConfByTaskCode(this.getKey());
        threadPoolConfList.stream().forEach(threadPoolConf -> {
            Integer taskLimitSize = threadPoolConf.getTaskLimitSize();
            String poolCode = threadPoolConf.getPoolCode();
            Integer poolQueueSize = ThreadPoolService.getPoolQueueSize(poolCode);
            if (poolQueueSize == null) {
                log.error("not yet init {} threadPool", this.getKey());
            } else {
                if (taskLimitSize != 0 && taskLimitSize > 0) {
                    this.setTaskLimitNum(taskLimitSize);
                }
                if (poolQueueSize <= this.getTaskLimitNum()) {
                    List<TaskInterface> taskList = taskInterfaceService.findAllByTaskCode(this.getTaskLimitNum(), poolCode);
                    taskList.stream().forEach(taskInterface -> taskInterface.setExecutionStatus(2));
                    taskInterfaceService.saveAllTaskInterface(taskList);
                    try {
                        taskList.stream().forEach(taskInterface -> {
                            ThreadPoolService.submit(cxt, poolCode, threadPoolConf.getBeanName(), taskInterface);
                        });
                    } catch (Exception e) {
                        taskList.stream().forEach(taskInterface -> taskInterface.setExecutionStatus(1));
                        taskInterfaceService.saveAllTaskInterface(taskList);
                    }
                }
            }
        });
    }
}
