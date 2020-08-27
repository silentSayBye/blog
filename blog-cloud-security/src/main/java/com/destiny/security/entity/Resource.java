package com.destiny.security.entity;


import com.blog.security.enums.HttpMethodEnum;
import com.blog.security.enums.ResourceType;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;


@Data
public class Resource {

    private Integer id;

    private String resourceName;

    private String  code;

    private String description;

    private String url;

    private Integer parentId;

    private Integer order;

    private Integer level;

    private ResourceType type;

    private HttpMethodEnum httpMethod;

    private int state;

    private List<Authority> authorityList = Lists.newArrayListWithCapacity(10);

}
