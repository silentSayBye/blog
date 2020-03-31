package com.destiny.api.domain.pojo;

import com.destiny.api.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Date 2020/2/14 16:38
 * @Version 1.0
 **/

@Entity
@Table(name = "d_article_content")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleContent extends BaseEntity<Integer> implements Serializable {

    @NotNull(message = "文章id不能为空")
    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "content")
    private String content;

    @NotNull(message = "排序编号不能为空")
    @Column(name = "order")
    private Integer order;
}
