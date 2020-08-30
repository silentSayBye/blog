package com.destiny.api;

import com.destiny.api.util.StringUtil;

import java.util.List;

/**
 * @Date 2020/8/29 21:24
 * @Version 1.0
 **/
public class StringUtilTest {
    public static void main(String[] args) {
        String a = "123456789";
        List<String> split = StringUtil.split(a, 3);
        for (String str :
                split) {
            System.out.println(str);
        }
    }
}
