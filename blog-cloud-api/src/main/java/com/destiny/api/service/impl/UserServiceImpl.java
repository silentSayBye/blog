package com.destiny.api.service.impl;

import com.destiny.security.utils.JwtUtil;
import com.destiny.api.dao.RoleRepository;
import com.destiny.api.dao.UserRepository;
import com.destiny.api.domain.dto.UserDto;
import com.destiny.api.domain.pojo.Authority;
import com.destiny.api.domain.pojo.Role;
import com.destiny.api.domain.pojo.User;
import com.destiny.api.exception.CustomException;
import com.destiny.api.service.RoleService;
import com.destiny.api.service.UserService;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    // 赋予注册用户初始权限
    @Value("${role.code}")
    private String roleCode;

    @Value("${role.name}")
    private String roleName;

    @Value("${role.description}")
    private String description;

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
     **/
    @Override
    public Set<Authority> findAuthorityByUsername(String username){
        List<Role> roles = findRoleByUsername(username);
        Set<Authority> authorities = Sets.newLinkedHashSet();
        for (Role role:roles){
            List<Authority> temp = role.getAuthorityList();
            authorities.addAll(temp);
        }
        return authorities;
    }

    @Override
    public User findByUsername(String username) {
        if (StringUtils.isBlank(username)){
            return null;
        }
        return userRepository.findUserInfo(username,null,1);
    }

    //通过用户名注册
    public UserDto register(String username,String password){
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            log.info("用户名 或者 密码 不能为空，当前用户名 为 {},密码为{}",username,password);
            throw new CustomException(String.format("用户名 或者 密码 不能为空，当前用户名为%s,密码为%s",username,password), HttpStatus.BAD_REQUEST);
        }

        //todo 添加验证码验证
        User  user = userRepository.findByUsername(username);
        if (user != null){
            return null;
        }

        User registerUser = User.builder().username(username).password(passwordEncoder.encode(password)).build();
        Role role = roleRepository.findByCodeAndState(roleCode, 1);
        if (role == null){
            role = roleService.insertRole(roleName, roleCode, description);
        }
        registerUser.setRoles(Arrays.asList(role));
        registerUser.setState(1);
        user = userRepository.save(registerUser);
        UserDto userDto = new UserDto();
        userDto.setUser(user);
        return  userDto;
    }

    @Override
    public String login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("密码不正确");
        }
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtUtil.generalAccessToken(userDetails);
        return token;
    }
}
