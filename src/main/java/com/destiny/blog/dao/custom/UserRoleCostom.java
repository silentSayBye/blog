package com.destiny.blog.dao.custom;

import com.destiny.blog.domain.pojo.UserRole;

import java.util.List;

/**
 * @ClassName UserRoleCostom
 * @Author Administrator
 * @Date 2019/6/1721:06
 * @Version 1.0
 **/
public interface UserRoleCostom {
    /**
     * @Author Administrator
     * @Description 通过userId roleId flag查询UserRole
     * @Date 21:09 2019/6/17
     * @Param [userId, roleId]
     * @Return java.util.List<com.destiny.blog.domain.pojo.UserRole>
     **/
    List<UserRole> findUserRoleInfo(Integer userId,Integer roleId,Integer flag);
}
