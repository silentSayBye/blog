package com.blog.gateway.service;

import com.blog.security.entity.LoginResult;

/**
 * @Date 2020/3/21 20:07
 * @Version 1.0
 **/

public interface UserService {

    LoginResult login(String username, String pssword);
}
