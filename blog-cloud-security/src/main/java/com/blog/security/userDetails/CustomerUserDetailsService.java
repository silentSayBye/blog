package com.blog.security.userDetails;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blog.security.entity.Authority;
import com.blog.security.entity.Response;
import com.blog.security.entity.User;
import com.blog.security.entity.UserDto;
import com.blog.security.exception.FailFetchInfoByFeignException;
import com.blog.security.service.UserRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Date 2020/3/6 21:57
 * @Version 1.0
 **/

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRemoteService userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(username,"用户名不能为空！");
        Response userResponse = userRepository.findByUsername(username);
        if (userResponse == null || "0".equals(userResponse.getCode())){
            throw new FailFetchInfoByFeignException("获取用户信息失败");
        }else {
            Map map = (Map)userResponse.getData();
            String userStr = JSONObject.toJSONString(map);
            User user = JSONObject.parseObject(userStr,User.class);
            if (user != null){
                Response authorityResponse = userRepository.findAuthorityByUsername(username);
                if (authorityResponse == null || "0".equals(authorityResponse.getCode())){
                    throw new FailFetchInfoByFeignException("获取用户权限失败");
                }
                List authoritiesTemp = (List) authorityResponse.getData();
                String authoritiesStr = JSON.toJSONString(authoritiesTemp);
                List<Authority> authorities = JSONObject.parseArray(authoritiesStr,Authority.class);
                Set<Authority> authoritySet = new HashSet<>(authorities);
                return new UserDto(user, authoritySet);
            }
            throw new UsernameNotFoundException("该用户不存在");
        }
    }
}
