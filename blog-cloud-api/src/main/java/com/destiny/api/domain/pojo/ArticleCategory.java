package com.destiny.api.domain.pojo;

import com.destiny.api.domain.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "d_article_category")
@Entity
@Data
public class ArticleCategory extends BaseEntity<Integer> implements Serializable{

    @Column(name = "category")
    private String category;

    @Column(name = "type")
    private Integer type;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private  Integer status;
}
