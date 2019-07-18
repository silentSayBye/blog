package com.destiny.blog.service.impl;

import com.destiny.blog.dao.ResourceRepository;
import com.destiny.blog.dao.RoleRepository;
import com.destiny.blog.dao.UserRepository;
import com.destiny.blog.domain.dto.UserDto;
import com.destiny.blog.domain.pojo.Resource;
import com.destiny.blog.domain.pojo.Role;
import com.destiny.blog.domain.pojo.User;
import com.destiny.blog.exception.CustomException;
import com.destiny.blog.service.UserService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    @SuppressWarnings("all")
    private AuthenticationManager authenticationManager;

    /**
     * @Author Administrator
     * @Description 查询所有用户 包括flag 1 和 0
     * @Date 23:01 2019/6/18
     * @Param [pageable]
     * @Return org.springframework.data.domain.Page<com.destiny.blog.domain.pojo.User>
     **/
    @Override
    public Page<User> findAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * @Author Administrator
     * @Description 根据flag和id查询用户
     * @Date 23:03 2019/6/18
     * @Param [id, flag]
     * @Return com.destiny.blog.domain.pojo.User
     **/
    @Override
    public User findUserById(Integer id, @Null Integer flag){
        return userRepository.findUserById(id,flag);
    }

    /**
     * @Author Administrator
     * @Description 查询所有flag为1 或者 0 的用户信息
     * @Date 23:06 2019/6/18
     * @Param [flag]
     * @Return java.util.List<com.destiny.blog.domain.pojo.User>
     **/
    @Override
    public List<User> findAllUser(@Null Integer flag){
        return userRepository.findAllByState(flag);
    }


    /**
     * @Author Administrator
     * @Description 通过 username 或者 email 查询 password
     * @Date 22:51 2019/6/18
     * @Param [username, email]
     * @Return java.lang.String
     **/
    @Override
    public String findPasswordByUsernameOrEmail(String username, String email){
        User user = userRepository.findUserInfo(username, email, 1);
        return user.getPassword();
    }

    /**
     * @Author Administrator
     * @Description 添加用户
     * @Date 22:57 2019/6/18
     * @Param [user]
     * @Return boolean
     **/
    @Override
    public boolean insertUser(User user){
        User save = userRepository.save(user);
        if (user != null){
            return true;
        }
        return false;
    }


    /**
     * @Author Administrator
     * @Description 逻辑删除用户
     * @Date 22:59 2019/6/18
     * @Param [id]
     * @Return boolean
     **/
    @Override
    public boolean deleteUser(Integer id){
        return userRepository.deleteUser(id);

    }

    /**
     * @Author Administrator
     * @Description 根据用户名查询用户权限
     * @Date 23:37 2019/6/18
     * @Param [username]
     * @Return java.util.List<com.destiny.blog.domain.pojo.Role>
     **/
    @Override
    public List<Role> findRoleByUsername(String username){
        User user = userRepository.findUserInfo(username, null, 1);
        List<Role> roles = user.getRoles();
        return roles;
    }

    /**
     * @Author Administrator
     * @Description 根据用户名查询用户资源
     * @Date 23:42 2019/6/18
     * @Param [username]
     * @Return java.util.Set<com.destiny.blog.domain.pojo.Resource>
     **/
    @Override
    public Set<Resource> findResourceByUsername(String username){
        List<Role> roles = findRoleByUsername(username);
        Set<Resource> resources = Sets.newLinkedHashSet();
        for (Role role:roles){
            List<Resource> temp = role.getResources();
            resources.addAll(temp);
        }
        return resources;
    }


    @Override
    public UserDetails loadUserByUsername(String nameOrEmail) throws UsernameNotFoundException{
        User userInfo = null;
        UserDto userDto= null;
        if (StringUtils.isBlank(nameOrEmail)){
            return null;
        }
       User user = userRepository.findUserInfoByUsernameOrEmail(nameOrEmail,1);
        if (user == null){
            throw new UsernameNotFoundException(String.format("No UserDetail Found With Name Or Email '%s'.",nameOrEmail));
        }
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        Set<Resource> resources = findResourceByUsername(user.getUsername());
        userDto.setResources(resources);
        return userDto;
    }


    //通过邮箱注册
    public UserDto register(String email,String password){
        User userInfo = new User();
        if (StringUtils.isAllBlank(email,password)){
            log.info("邮箱 或者 密码 不能为空，当前 邮箱 为 {},密码为{}",email,password);
            throw new CustomException(String.format("邮箱 或者 密码 不能为空",email,password));
        }

        //todo 添加验证码验证
        User  user = userRepository.findByEmailAndState(email, 1);
        if (user != null){

        }
        return  null;
    }
}
