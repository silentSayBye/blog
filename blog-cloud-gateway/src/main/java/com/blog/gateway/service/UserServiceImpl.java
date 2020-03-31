package com.blog.gateway.service;

import com.blog.security.entity.LoginResult;
import com.blog.security.entity.UserDto;
import com.blog.security.userDetails.CustomerUserDetailsService;
import com.blog.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;


/**
 * @Date 2020/3/21 20:13
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private CustomerUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public LoginResult login(@Null(message = "用户名不能为空") String username, String password){
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null){
            throw new UsernameNotFoundException("用户未注册");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("密码不正确");
        }
        String token = jwtUtil.generalAccessToken(userDetails);
        LoginResult result = new LoginResult();
        UserDto userDto = (UserDto) userDetails;
        result.setToken(token);
        result.setUser(userDto.getUser());
        return result;
    }
}
