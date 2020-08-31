package com.destiny.common.taskschedule.task;

import com.destiny.common.domain.pojo.TaskInterface;
import com.destiny.common.service.TaskInterfaceService;
import com.destiny.common.taskschedule.AbstractThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Scope("prototype")
@Component
public class PasswordConfirm extends AbstractThread<TaskInterface> {

    @Autowired
    private TaskInterfaceService taskInterfaceService;

    @Override
    public void exec(TaskInterface data) {
        System.out.println("执行定时任务。。。。");
        data.setExecutionStatus(3);
        taskInterfaceService.updateTaskInterface(data);
    }
}
