package com.destiny.blog.domain.pojo;

import com.destiny.blog.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "d_role_resource")
@Setter
@Getter
public class RoleResource extends BaseEntity<Integer> implements Serializable {

    @Column(name = "resource_id")
    private Integer resourceId;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "state")
    private Integer state;
}
