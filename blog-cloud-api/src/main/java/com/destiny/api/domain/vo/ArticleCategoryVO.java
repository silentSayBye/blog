package com.destiny.api.domain.vo;

import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleCategoryVO {

    private Integer id;

    private String category;

    @NotNull
    private Integer type;

    private String description;

    @NotNull
    private  Integer status;
}
