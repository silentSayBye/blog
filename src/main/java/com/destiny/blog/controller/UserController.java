package com.destiny.blog.controller;

import com.destiny.blog.dao.UserRepository;
import com.destiny.blog.domain.dto.JwtVo;
import com.destiny.blog.domain.pojo.User;
import com.destiny.blog.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

/**
 * @ClassName UserController
 * @Author Administrator
 * @Date 2019/6/2422:09
 * @Version 1.0
 **/
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/")
    public JwtVo login(String username, String email, String password) throws Exception{
        JwtVo jwtVo = new JwtVo();
//        if (StringUtils.isNotBlank(password)){
//            if (StringUtils.isNotBlank(username) || StringUtils.isNotBlank(email)){
//                User userInfo = userRepository.findUserInfo(username, email, 1);
//                // todo  password解密 判断
//                String jwt = JwtUtil.createJwt(UUID.randomUUID().toString(), userInfo.getEmail(), userInfo.getUsername(), 3600L);
//                jwtVo.setCode(HttpStatus.OK);
//                jwtVo.setToken(jwt);
//                jwtVo.setMessage("success");
//                jwtVo.setData(userInfo);
//            }
//        }else{
//            jwtVo.setCode(HttpStatus.BAD_REQUEST);
//            jwtVo.setMessage("password can not be empty");
//        }
       return jwtVo;
    }
}
