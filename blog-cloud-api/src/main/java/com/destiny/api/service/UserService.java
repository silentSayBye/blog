package com.destiny.api.service;

import com.destiny.api.domain.dto.UserDto;
import com.destiny.api.domain.pojo.Authority;
import com.destiny.api.domain.pojo.Role;
import com.destiny.api.domain.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;


public interface UserService {

    Page<User> findAllUser(Pageable pageable);

    User findUserById(Integer id, @Null Integer flag);

    List<User> findAllUser(@Null Integer flag);

    String findPasswordByUsernameOrEmail(String username, String email);

    boolean insertUser(User user);

    boolean deleteUser(Integer id);

    List<Role> findRoleByUsername(String username);

    Set<Authority> findAuthorityByUsername(String username);

    User findByUsername(String username);

    UserDto register(String username, String password);

    String login (String username, String password);
}
