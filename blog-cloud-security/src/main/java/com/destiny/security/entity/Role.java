package com.destiny.security.entity;

import com.google.common.collect.Lists;
import lombok.*;

import java.util.List;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer id;

    private String roleName;

    private String code;

    private String description;

    private Integer state;

    private List<Authority> authorityList = Lists.newArrayListWithCapacity(10);

}
