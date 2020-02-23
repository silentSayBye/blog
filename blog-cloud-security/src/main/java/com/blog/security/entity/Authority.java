package com.blog.security.entity;

import lombok.Data;

/**
 * @Date 2020/2/15 15:56
 * @Version 1.0
 **/

@Data
public class Authority {

    private Integer id;

    private String name;

    private String code;

    private String description;

    private Integer state;
}
