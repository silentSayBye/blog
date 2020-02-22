package com.destiny.api.service.impl;

import com.destiny.api.dao.RoleRepository;
import com.destiny.api.domain.pojo.Role;
import com.destiny.api.exception.CustomException;
import com.destiny.api.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @Date 2020/2/10 21:15
 * @Version 1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role insertRole(String roleName, String roleCode, String description) {

        if (StringUtils.isBlank(roleCode)){
            throw new CustomException("roleCode 不能为空！", HttpStatus.BAD_REQUEST);
        }
        // roleCode唯一
        Role role = roleRepository.findByCodeAndState(roleCode, 1);
        if (role != null){
            return role;
        }
        role = Role.builder()
                .code(roleCode)
                .state(1)
                .description(description)
                .roleName(roleName)
                .build();
        return roleRepository.save(role);
    }
}
