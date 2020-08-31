package com.destiny.common.domain.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "d_thread_pool_conf")
public class ThreadPoolConf extends BaseEntity<Long> implements Serializable {

    @Column(name = "task_code")
    private String taskCode;

    @Column(name = "pool_code")
    private String poolCode;

    @Column(name = "pool_name")
    private String poolName;

    @Column(name = "bean_name")
    private String beanName;

    @Column(name = "wait_time")
    private Integer waitTime;

    @Column(name = "pool_size")
    private Integer poolSize;

    @Column(name = "pool_queue_size")
    private Integer poolQueueSize;

    @Column(name = "task_limit_size")
    private Integer taskLimitSize;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

}
