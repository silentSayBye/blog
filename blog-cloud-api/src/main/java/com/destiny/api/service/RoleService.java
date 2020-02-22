package com.destiny.api.service;

import com.destiny.api.domain.pojo.Role;

/**
 * @ClassName RoleService
 * @Author Administrator
 * @Date 2020/2/1021:14
 * @Version 1.0
 **/
public interface RoleService {

    Role insertRole(String roleName, String roleCode, String description);
}
