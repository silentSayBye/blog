package com.destiny.security.entity;

import com.google.common.collect.Lists;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer id;

    private String username;

    private String  password;

    private String email;

    private Integer state;

    private String img;

    private String code;

    private List<Role> roles = Lists.newArrayList();
}
