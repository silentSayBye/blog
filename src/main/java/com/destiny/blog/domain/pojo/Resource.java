package com.destiny.blog.domain.pojo;

import com.destiny.blog.domain.enums.HttpMethodEnum;
import com.destiny.blog.domain.enums.ResourceType;
import com.destiny.blog.util.BaseEnum;
import com.destiny.blog.util.enumconverter.HttpMethodConverter;
import com.destiny.blog.util.enumconverter.ResourceTypeConverter;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "d_resource")
public class Resource extends BaseEntity implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "resource_name")
    private String resourceName;

    @Column(name = "resource_code")
    private String  code;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "order")
    private Integer order;

    @Column(name = "level")
    private Integer level;

    @Column(name = "type")
    @Convert(converter = ResourceTypeConverter.class)
    private ResourceType type;

    @Column(name = "http_method")
    @Convert(converter = HttpMethodConverter.class)
    private HttpMethodEnum httpMethod;

    @Column(name = "state")
    private int deleteFlag;

    @ManyToMany(mappedBy = "resources")
    private List<Role> roles = Lists.newArrayList();
}
