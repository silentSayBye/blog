package com.destiny.blog.domain.pojo;

import com.destiny.blog.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Date 2020/2/15 16:08
 * @Version 1.0
 **/
@Setter
@Getter
@Entity
@Table(name = "d_resource_authority")
public class ResourceAuthority extends BaseEntity<Integer> implements Serializable {

    @Column(name = "resource_id")
    private Integer resourceId;

    @Column(name = "authority_id")
    private Integer authorityId;

    @Column(name = "state")
    private Integer state;
}
