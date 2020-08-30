package com.destiny.api.domain.vo;

import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomCategoryVO {

    private Integer id;

    private String category;

    @NotNull
    private Integer userId;

    private String description;

    @NotNull
    private  Integer status;
}
