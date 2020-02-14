package com.destiny.blog.dao.custom;

import com.destiny.blog.domain.pojo.Resource;
import com.destiny.blog.domain.pojo.User;

import java.util.Set;

public interface UserCustom {

    User findUserInfo(String name,String email,Integer flag);

    User findUserInfoByUsernameOrEmail(String name,Integer flag);

    Set<Resource> findResourceByUsername(String username);
}
