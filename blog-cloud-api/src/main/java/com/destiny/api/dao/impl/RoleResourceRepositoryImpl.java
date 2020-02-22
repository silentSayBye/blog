package com.destiny.api.dao.impl;/*
package com.destiny.api.dao.impl;

import com.destiny.api.dao.BaseHibernate;
import com.destiny.api.dao.custom.RoleResourceCustom;
import com.destiny.api.domain.pojo.RoleResource;
import com.google.common.collect.Maps;

import java.util.Map;

*/
/**
 * @ClassName RoleResourceRepositoryImpl
 * @Author Administrator
 * @Date 2019/6/1723:13
 * @Version 1.0
 **//*

public class RoleResourceRepositoryImpl extends BaseHibernate<RoleResource> implements RoleResourceCustom {

    @Override
    public RoleResource findRoleResourceInfo(Integer resourceId, Integer roleId, Integer flag) {
        String str = "select * from d_role_resource where 1 = 1";
        StringBuffer sql = new StringBuffer(str);
        Map<String,Object> conditions = Maps.newHashMap();
        if (resourceId != null){
            sql.append(" and resource_id = :resourceId");
            conditions.put("resourceId",resourceId);
        }
        if (roleId != null){
            sql.append(" and role_id = :roleId");
            conditions.put("roleId",roleId);
        }
        if (flag != null){
            conditions.put("flag",flag);
            sql.append(" and state = :flag");
        }else{
            conditions.put("flag",1);
            sql.append(" and state = :flag");
        }
        RoleResource roleResource = findObjectByCondition(sql.toString(), conditions);
        return roleResource;
    }
}
*/
