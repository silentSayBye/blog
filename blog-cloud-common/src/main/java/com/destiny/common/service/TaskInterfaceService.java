package com.destiny.common.service;

import com.destiny.common.domain.pojo.TaskInterface;

import java.util.List;

public interface TaskInterfaceService {

    TaskInterface findByTaskId(Integer taskId);

    TaskInterface findById(Long id);

    List<TaskInterface> findAllByStatus(Integer status);

    void deleteTaskInterface(Long id);

    void updateTaskInterface(TaskInterface taskInterface);

    List<TaskInterface> findAllByTaskCode(Integer limitNum, String taskCode);

    void saveAllTaskInterface(List<TaskInterface> taskInterfaceList);
}
