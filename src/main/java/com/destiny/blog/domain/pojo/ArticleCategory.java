package com.destiny.blog.domain.pojo;

import com.destiny.blog.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "d_article_category")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleCategory extends BaseEntity<Integer> implements Serializable{


    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "delete_flag")
    private  Integer deleteFlag;

}
