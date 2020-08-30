package com.destiny.api.util;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
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

    /**
     * 分割字符串
     * @Param content 字符串内容 length 分割块长度
     * @Return
     **/
    public static List<String> split(String content, int length) {
        if (StringUtils.isBlank(content)){
            return Collections.emptyList();
        }
        List<String> list = Lists.newArrayList();
        if (length == 0){
            list.add(content);
            return list;
        }
        int len = 0;
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < content.length(); i++){
            len += content.charAt(i) <= 256 ? 1 : 2;
            if (len > length){
                list.add(temp.toString());
                temp = new StringBuffer();
                len = 0;
                len += content.charAt(i) <= 256 ? 1 : 2;
                temp.append(content.charAt(i));
                continue;
            }
            temp.append(content.charAt(i));
        }
        if (temp.length() >0){
            list.add(temp.toString());
        }
        return list;
    }
}
