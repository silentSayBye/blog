package com.destiny.api;

import com.destiny.api.util.StringUtil;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * @Date 2020/4/2 21:23
 * @Version 1.0
 **/
public class StringUtilTest {

    public static void splitContent(){
        String str = "站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈换" +
                "站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈换" +
                "站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈发站但你哈换" +
                "站zgabdab";

        List<String> list = StringUtil.splitContent(str, 150);
        Assert.assertEquals(4,list.size());
//        list.forEach(System.out::println);
    }

    public static void mergeToString(){
        List<String> list = Arrays.asList("栈上的飞机啊开了房间","merge","toString");
        String mergeToString = StringUtil.mergeToString(list);
        Assert.assertEquals("栈上的飞机啊开了房间mergetoString",mergeToString);
    }



    public static void main(String[] args) {
        StringUtilTest.splitContent();
        StringUtilTest.mergeToString();

    }
}
