package com.destiny.blog.domain.pojo;

import com.destiny.blog.domain.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "d_article")
public class Article extends BaseEntity<Integer> implements Serializable {


    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content1")
    private String content1;

    @Lob
    @Column(name = "content2")
    private String content2;

    @Column(name = "delete_flag")
    private Integer deleteFlag;
}
