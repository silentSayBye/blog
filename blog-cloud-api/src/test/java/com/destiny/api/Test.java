package com.destiny.api;

import com.google.common.collect.Maps;
import org.springframework.util.ClassUtils;

import java.util.Map;

/**
 * @Date 2020/8/29 16:41
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        Map map = Maps.newHashMap();
        Integer a = 3;

        System.out.println(a.getClass().isPrimitive());
        System.out.println(ClassUtils.isPrimitiveOrWrapper(a.getClass()));
        System.out.println(a.getClass());
    }
}
