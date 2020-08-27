package com.destiny.common.domain.pojo;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "task_interface")
@Data
public class TaskInterface extends BaseEntity<Long> implements Serializable{

    @NonNull
    @Column(name = "task_id")
    private String taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_type")
    private String taskType;

    @Column(name = "sub_task_type")
    private String subTaskType;

    @Column(name = "recent_execute_time")
    private Date recentExecuteTime;

    @Column(name = "next_execute_time")
    private Date nextExecuteTime;

    @Column(name = "failed_num")
    private Integer failedNum;

    @Column(name = "max_num")
    private Integer maxNum;

    @Column(name = "execution_status")
    private Integer executionStatus;

    @Column(name = "description")
    private String description;

    @Column(name = "send_email_flag")
    private Boolean sendEmailFlag;

    @Column(name = "status")
    private Integer status;
}
