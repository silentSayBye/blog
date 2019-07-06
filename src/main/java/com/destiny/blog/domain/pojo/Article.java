package com.destiny.blog.domain.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "d_article")
public class Article extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
