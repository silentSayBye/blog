package com.destiny.blog.domain.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "d_role_resource")
@Setter
@Getter
public class RoleResource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "resource_id")
    private Integer resourceId;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "state")
    private Integer deleteFlag;
}
