package com.destiny.blog.controller;

import com.destiny.blog.domain.dto.UserDto;
import com.destiny.blog.domain.pojo.User;
import com.destiny.blog.domain.vo.Response;
import com.destiny.blog.exception.CustomException;
import com.destiny.blog.service.UserService;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName UserController
 * @Author Administrator
 * @Date 2019/6/2422:09
 * @Version 1.0
 **/

@RestController
@Slf4j
public class UserController {


    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @PostMapping(path = "/login")
    public Response login(@RequestBody User user) throws Exception{
        String token = null;
        try{
            token = userService.login(user.getUsername(), user.getPassword());
        }catch (CustomException e){
            return Response.failed("登录失败");
        }
        Map<String,String> response = Maps.newHashMap();
        response.put("token",token);
        return Response.success(response);
    }

    @ApiOperation("用户注册")
    @PostMapping(path = "/register")
    public Response register(@RequestBody User user) throws Exception{
        UserDto userDto = userService.register(user.getUsername(), user.getPassword());
        if (userDto == null){
            return Response.success("用户已存在");
        }
        return Response.success("注册成功");
    }
}