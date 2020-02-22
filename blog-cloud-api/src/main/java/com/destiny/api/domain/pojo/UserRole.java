package com.destiny.api.domain.pojo;

import com.destiny.api.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "d_user_role")
@Setter
@Getter
@ToString
public class UserRole extends BaseEntity<Integer> implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "state")
    private Integer state;

}
