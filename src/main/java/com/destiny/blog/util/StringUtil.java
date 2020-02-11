package com.destiny.blog.util;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName StringUtil
 * @Author Administrator
 * @Date 2019/6/1723:29
 * @Version 1.0
 **/
public class StringUtil {


    public final static String[] flagList = {"0","1"};

    public static boolean isEmail(String email){
        if (StringUtils.isBlank(email)){
            return false;
        }
        String  regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()){
            return true;
        }
        return false;
    }
    public static boolean isPhone(String phone){
        if (StringUtils.isBlank(phone)){
            return false;
        }
        String regex = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()){
            return true;
        }
        return false;
    }

}
