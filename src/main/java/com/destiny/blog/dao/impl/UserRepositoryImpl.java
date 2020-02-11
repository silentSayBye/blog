package com.destiny.blog.dao.impl;

import com.destiny.blog.dao.BaseHibernate;
import com.destiny.blog.dao.custom.UserCustom;
import com.destiny.blog.domain.pojo.User;
import com.destiny.blog.exception.CommomException;
import com.destiny.blog.util.StringUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @ClassName UserRepositotyImpl
 * @Author Administrator
 * @Date 2019/6/1614:26
 * @Version 1.0
 **/
@Slf4j
public class UserRepositoryImpl extends BaseHibernate<User> implements UserCustom {


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
