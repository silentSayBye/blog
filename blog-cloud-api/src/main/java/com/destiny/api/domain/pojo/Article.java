package com.destiny.api.domain.pojo;

import com.destiny.api.domain.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "d_article")
public class Article extends BaseEntity<Integer> implements Serializable {

    @Column(name = "title")
    private String title;

    @Column(name = "system_class")
    private Integer systemClass;

    @Column(name = "user_class")
    private Integer userClass;

    @Column(name = "type")
    private Integer type;

    @Column(name = "publish_type")
    private Integer publishType;

    @Column(name = "status")
    private Integer status;
}
