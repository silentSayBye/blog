package com.destiny.api.dao.impl;

import com.destiny.api.dao.BaseHibernate;
import com.destiny.api.dao.custom.UserRoleCostom;
import com.destiny.api.domain.pojo.UserRole;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserRoleRepositoryImpl
 * @Author Administrator
 * @Date 2019/6/1721:10
 * @Version 1.0
 **/
public class UserRoleRepositoryImpl extends BaseHibernate<UserRole> implements UserRoleCostom {

    @Override
    public List<UserRole> findUserRoleInfo(Integer userId, Integer roleId, Integer flag) {
        String str = "select * from d_user_role where 1 = 1";
        StringBuffer sql = new StringBuffer(str);
        Map<String,Object> conditions = Maps.newHashMap();
        if (userId != null){
            sql.append(" and user_id = :userId");
            conditions.put("userId",userId);
        }
        if (roleId != null){
            sql.append(" and role_id = :roleId");
            conditions.put("roleId",roleId);
        }
        if (flag != null){
            sql.append(" and state = :flag");
            conditions.put("flag",flag);
        }else {
            sql.append(" and state = :flag");
            conditions.put("flag",1);
        }
        List<UserRole> userRoleList = findAllObjectByCondition(sql.toString(), conditions);
        return userRoleList;
    }
}
