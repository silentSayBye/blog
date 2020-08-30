package com.destiny.api.domain.pojo;

import com.destiny.api.domain.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Date 2020/8/30 1:51
 * @Version 1.0
 **/

@Data
@Entity
@Table(name = "d_custom_category")
public class CustomCategory extends BaseEntity<Long> implements Serializable {

    @Column(name = "category")
    private String category;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private  Integer status;
}
