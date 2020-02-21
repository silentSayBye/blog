package com.destiny.blog.domain.pojo;

import com.destiny.blog.domain.base.BaseEntity;
import com.google.common.collect.Lists;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "d_role")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity<Integer> implements Serializable {

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "state")
    private Integer state;

    @ManyToMany(mappedBy = "roles" )
    private List<User> users;

    @ManyToMany
    @JoinTable(name = "d_role_authority", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {
            @JoinColumn(name = "authority_id")
    })
    private List<Authority> authorityList = Lists.newArrayListWithCapacity(10);

}
