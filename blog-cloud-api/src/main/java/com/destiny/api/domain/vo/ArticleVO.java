package com.destiny.api.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Date 2020/8/29 20:04
 * @Version 1.0
 **/
@Data
public class ArticleVO {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Integer systemClass;

    @NotNull
    private Integer userClass;

    @NotNull
    private Integer type;

    @NotNull
    private Integer publishType;
}
