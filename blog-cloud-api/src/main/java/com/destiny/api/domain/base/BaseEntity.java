package com.destiny.api.domain.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<PK>  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected PK id;

    @CreatedBy
    @Column(name = "creator")
    protected String creator;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    protected Date createTime;

    @LastModifiedBy
    @Column(name = "updater")
    protected String updater;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    protected Date updateTime;
}
