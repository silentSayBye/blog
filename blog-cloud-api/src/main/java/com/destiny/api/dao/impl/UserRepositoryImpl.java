package com.destiny.api.dao.impl;

import com.destiny.api.util.StringUtil;
import com.destiny.api.dao.BaseHibernate;
import com.destiny.api.dao.custom.UserCustom;
import com.destiny.api.domain.pojo.Authority;
import com.destiny.api.domain.pojo.User;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * @ClassName UserRepositotyImpl
 * @Author Administrator
 * @Date 2019/6/1614:26
 * @Version 1.0
 **/
@Slf4j
public class UserRepositoryImpl extends BaseHibernate<User> implements UserCustom {

    @Override
    public Set<Authority> findAuthorityByUsername(String username) {
        User user = findUserInfo(username, null, 1);
        Set<Authority> authorities = Sets.newHashSet();
        user.getRoles().forEach(role ->
                authorities.addAll(role.getAuthorityList())
         );
        return authorities;
    }

    @Override
    public User findUserInfo(String name, String email, Integer flag) {
        Map<String,Object> conditions = Maps.newHashMap();
        StringBuffer sql = new StringBuffer();
        sql = sql.append("select * from d_user where 1 = 1");
        if(StringUtils.isNotBlank(name)){
            conditions.put("name",name);
            sql.append(" and user_name = :name");
        }
        if(StringUtils.isNotBlank(email)){
            conditions.put("email",email);
            sql.append(" and email = :email");
        }
        if(flag != null){
            conditions.put("flag",flag);
            sql.append(" and state = :flag");
        }else {
            conditions.put("flag",1);
            sql.append(" and state = :flag");
        }
        User user = findObjectByCondition(sql.toString(), conditions);
        return user;
    }

    @Override
    public User findUserInfoByUsernameOrEmail(String name, Integer flag) {
        Map<String,Object> conditions = Maps.newHashMap();
        String sql = "";
        if (StringUtil.isEmail(name)){
             sql = "select * from d_user where email = :email";
            conditions.put("email",name);
        }else {
             sql = "select * from d_user where user_name = :username";
            conditions.put("username",name);
        }
        if (StringUtils.isNotBlank(flag.toString())){
            conditions.put("flag",flag);
        }else{
            conditions.put("flag",1);
        }
       sql+=" and state = :flag";
        return findObjectByCondition(sql,conditions);
    }
}
