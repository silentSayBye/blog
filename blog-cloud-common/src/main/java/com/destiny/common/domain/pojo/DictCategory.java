package com.destiny.common.domain.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "dict_category")
public class DictCategory extends BaseEntity<Long> implements Serializable {

    private String categoryCode;

    private String categoryName;

    private String description;

    private Integer status;
}
