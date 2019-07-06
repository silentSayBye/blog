package com.destiny.blog.domain.dto;

import com.destiny.blog.domain.pojo.Resource;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @ClassName UserDto
 * @Author Administrator
 * @Date 2019/7/322:15
 * @Version 1.0
 **/
@Getter
@Setter
public class UserDto implements UserDetails {

    private String username;
    private String email;
    private String password;
    private Set<Resource> resources;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = Lists.newArrayList();
        for (Resource resource: resources) {
            list.add(new SimpleGrantedAuthority(resource.getCode()));
        }
        return list;
    }

    @Override
    public String getPassword() {
       return password;
    }

    @Override
    public String getUsername() {
        if (StringUtils.isNotBlank(username)){
            return username;
        }else if (StringUtils.isNotBlank(email)){
            return email;
        }
        return null;
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
