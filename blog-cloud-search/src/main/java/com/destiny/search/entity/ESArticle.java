package com.destiny.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2020/4/13 21:39
 * @Version 1.0
 **/

@Data
@Document(indexName = "article", type = "article", shards = 1, replicas = 0)
public class ESArticle implements Serializable {

    private static final long serialVersionUID = -5057774232948773045L;

    @Id
    private Long id;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String title;

    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Keyword)
    private Integer systemClass;

    @Field(type = FieldType.Keyword)
    private Integer userClass;

    @Field(type = FieldType.Keyword)
    private Integer type;

    @Field(type = FieldType.Keyword)
    private Integer publishType;

    private Integer status;

    private String creator;

    private Date createTime;

    private String updater;

    private Date updateTime;
}
