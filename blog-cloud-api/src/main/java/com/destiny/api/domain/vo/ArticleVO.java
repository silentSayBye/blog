package com.destiny.api.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Date 2020/4/1 23:18
 * @Version 1.0
 **/

@Data
public class ArticleVO {

    private Integer articleId;

    @NotNull(message = "Article title cannot be null.")
    private String title;

    private Integer systemClass;

    private Integer userClass;

    private Integer type;

    private Integer publishType;

    @NotNull(message = "Article status cannot be null.")
    private Integer status;

    private String content;
}
