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

    @NotNull(message = "类别不能为空")
    private Integer type;

    private String description;

    @NotNull(message = "状态代码不能为空")
    private  Integer status;
}
