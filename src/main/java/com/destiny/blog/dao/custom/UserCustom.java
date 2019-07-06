package com.destiny.blog.dao.custom;

import com.destiny.blog.domain.pojo.User;

public interface UserCustom {

    User findUserInfo(String name,String email,Integer flag);

    User findUserInfoByUsernameOrEmail(String name,Integer flag);
}
