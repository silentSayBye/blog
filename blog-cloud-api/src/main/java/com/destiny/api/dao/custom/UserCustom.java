package com.destiny.api.dao.custom;

import com.destiny.api.domain.pojo.Authority;
import com.destiny.api.domain.pojo.User;

import java.util.Set;

public interface UserCustom {

    User findUserInfo(String name, String email, Integer flag);

    User findUserInfoByUsernameOrEmail(String name, Integer flag);

    Set<Authority> findAuthorityByUsername(String username);
}
