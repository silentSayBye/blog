package com.destiny.blog.service.impl;

import com.destiny.blog.dao.RoleRepository;
import com.destiny.blog.domain.pojo.Role;
import com.destiny.blog.exception.CustomException;
import com.destiny.blog.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName RoleServiceImpl
 * @Author Administrator
 * @Date 2020/2/1021:15
 * @Version 1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role insertRole(String roleName, String roleCode, String description) {

        if (StringUtils.isBlank(roleCode)){
            throw new CustomException("roleCode 不能为空！");
        }
        // roleCode唯一
        Role role = roleRepository.findByCodeAndDeleteFlag(roleCode, 1);
        if (role != null){
            return role;
        }
        role = Role.builder()
                .code(roleCode)
                .deleteFlag(1)
                .description(description)
                .roleName(roleName)
                .build();
        return roleRepository.save(role);
    }
}
