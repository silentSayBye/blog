package com.destiny.api.domain.pojo;

import com.destiny.api.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Date 2020/2/15 16:01
 * @Version 1.0
 **/

@Setter
@Getter
@Entity
@Table(name = "d_role_authority")
public class RoleAuthority extends BaseEntity<Integer> implements Serializable {
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "authority_id")
    private Integer authorityId;

    @Column(name = "state")
    private Integer state;
}
