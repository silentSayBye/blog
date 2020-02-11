package com.destiny.blog.domain.dto;

import com.destiny.blog.domain.pojo.Resource;
import com.destiny.blog.domain.pojo.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Date 2019/7/3 22:15
 * @Version 1.0
 **/
@Getter
@Setter
@ToString
public class UserDto implements UserDetails {

    private User user;
    private Set<Resource> resources;

    public UserDto(){}

    public UserDto (User user, Set<Resource> resources){
        this.user = user;
        this.resources = resources;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return resources.stream().filter(resource -> resource.getCode() != null)
            .map(resource -> new SimpleGrantedAuthority(resource.getCode()))
            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
