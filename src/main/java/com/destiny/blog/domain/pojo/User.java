package com.destiny.blog.domain.pojo;

import com.destiny.blog.domain.base.BaseEntity;
import com.google.common.collect.Lists;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "d_user")
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseEntity<Integer> implements Serializable {

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String  password;

    @Column(name = "email")
    private String email;

    @Column(name = "state")
    private Integer state;

    @Column(name = "img")
    private String img;

    @Column(name = "code")
    private String code;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "d_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns =
    @JoinColumn(name = "role_id"))
    private List<Role> roles = Lists.newArrayList();
}
