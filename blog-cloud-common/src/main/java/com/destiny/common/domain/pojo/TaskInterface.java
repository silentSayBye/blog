package com.destiny.common.domain.pojo;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "task_interface")
@Data
public class TaskInterface extends BaseEntity<Long> implements Serializable{

    @NonNull
    private String taskId;

    private String taskName;

    private String taskType;

    private String subTaskType;

    private Date recentExecuteTime;

    private Date nextExecuteTime;

    private Integer failedNum;

    private Integer maxNum;

    private Integer executionStatus;

    private String description;

    private Boolean sendEmailFlag;

    private Integer status;

}
