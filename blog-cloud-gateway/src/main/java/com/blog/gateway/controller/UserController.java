package com.blog.gateway.controller;

import com.blog.gateway.exception.CustomException;
import com.blog.gateway.service.UserService;
import com.blog.security.entity.LoginResult;
import com.blog.security.entity.Response;
import com.blog.security.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2020/3/21 20:32
 * @Version 1.0
 **/

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户登录")
    @PostMapping(path = "/login")
    public Response login(@RequestBody User user){
        LoginResult result = null;
        try{
            result = userService.login(user.getUsername(), user.getPassword());
        }catch (CustomException e){
            return Response.failed("登录失败");
        }
        return Response.success(result);
    }
}
