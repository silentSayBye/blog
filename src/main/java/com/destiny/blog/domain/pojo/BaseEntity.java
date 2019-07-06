package com.destiny.blog.domain.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
//@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity  {

    @CreatedBy
    @Column(name = "creator")
    private String creator;

    @CreatedDate
    @Temporal(TemporalType.TIME)
    @Column(name = "create_date")
    private Date createTime;

    @LastModifiedBy
    @Column(name = "updater")
    private String updater;

    @LastModifiedDate
    @Temporal(TemporalType.TIME)
    @Column(name = "update_date")
    private Date updateTime;
}
