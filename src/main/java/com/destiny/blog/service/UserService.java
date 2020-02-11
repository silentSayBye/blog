package com.destiny.blog.service;

import com.destiny.blog.domain.dto.UserDto;
import com.destiny.blog.domain.pojo.Resource;
import com.destiny.blog.domain.pojo.Role;
import com.destiny.blog.domain.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    Set<Resource> findResourceByUsername(String username);

    User findByUsername(String username);

    UserDto register(String username, String password);

    String login (String username, String password);
}
