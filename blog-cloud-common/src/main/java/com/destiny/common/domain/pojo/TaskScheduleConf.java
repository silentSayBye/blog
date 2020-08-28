package com.destiny.common.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "d_task_schedule_conf")
public class TaskScheduleConf {

    @Column(name = "task_code")
    private String taskCode;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "bean_name")
    private String beanName;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "cron")
    private String corn;

    @Column(name = "if_lock")
    private Boolean ifLock;

    @Column(name = "lock_time")
    private Integer lockTime;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

}
