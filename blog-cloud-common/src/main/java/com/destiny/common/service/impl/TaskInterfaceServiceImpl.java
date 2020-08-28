package com.destiny.common.service.impl;

import com.destiny.common.dao.TaskInterfaceRepository;
import com.destiny.common.domain.pojo.TaskInterface;
import com.destiny.common.service.TaskInterfaceService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskInterfaceServiceImpl implements TaskInterfaceService {

    @Autowired
    private TaskInterfaceRepository taskInterfaceRepository;

    @Override
    public TaskInterface findByTaskId(Integer taskId) {
        Preconditions.checkNotNull(taskId);
        return taskInterfaceRepository.findByTaskId(taskId);
    }

    @Override
    public TaskInterface findById(Long id) {
        Preconditions.checkNotNull(id);
        return taskInterfaceRepository.findById(id).orElseGet(() -> new TaskInterface());
    }

    @Override
    public List<TaskInterface> findAllByStatus(Integer status) {
        Preconditions.checkNotNull(status);
        return taskInterfaceRepository.findAllByStatus(status);
    }

    @Override
    public void deleteTaskInterface(Long id) {
        Preconditions.checkNotNull(id);
        Optional<TaskInterface> taskInterfaceOptional = taskInterfaceRepository.findById(id);
        if (taskInterfaceOptional.isPresent()) {
            TaskInterface taskInterface = taskInterfaceOptional.get();
            taskInterface.setStatus(0);
            taskInterfaceRepository.save(taskInterface);
        }
    }

    @Override
    public void updateTaskInterface(TaskInterface taskInterface) {
        taskInterfaceRepository.save(taskInterface);
    }
}
