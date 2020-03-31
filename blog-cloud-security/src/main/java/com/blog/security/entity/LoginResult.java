package com.blog.security.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Date 2020/3/21 20:10
 * @Version 1.0
 **/
@Data
public class LoginResult implements Serializable {
    private User user;
    private String token;
}
