package com.destiny.api.util;


import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
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

    public static String mergeToString(List<String> list){
        StringBuffer sb = new StringBuffer();
        list.forEach(str -> sb.append(str));
        return sb.toString();
    }


    /**
     * content 被分割的内容
     * size 分割的大小
     **/
    public static List<String> splitContent(String content, Integer size){
        Preconditions.checkArgument(StringUtils.isNotBlank(content),"Content cannot be null.");
        Preconditions.checkArgument(size != null && size > 100,"Size cannot be null and must be greater than 100.");
        List<String> blocks = Lists.newArrayListWithCapacity(16);
        int startIndex = 0;
        int endIndex;
        int tempIndex = startIndex;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < content.length(); i++){
            endIndex = startIndex +1;
            String substring = content.substring(startIndex, endIndex);
            int length =  content.substring(tempIndex, endIndex).getBytes(StandardCharsets.UTF_8).length;
            if (length > size){
                blocks.add(sb.toString());
                sb = new StringBuffer();
                sb.append(substring);
                tempIndex = startIndex;
                startIndex++;
                continue;
            }
            sb.append(substring);
            startIndex++;
        }
        blocks.add(sb.toString());
        return blocks;
    }
}