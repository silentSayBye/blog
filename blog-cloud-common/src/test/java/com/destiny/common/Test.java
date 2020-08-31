package com.destiny.common;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<User> list = Lists.newArrayList();
//        List<Integer> collect = list.stream().map(User::getAge).collect(Collectors.toList());
//        System.out.println(collect);
        list.stream().forEach(user -> System.out.println("===" + user.getAge()));
    }

    @Data
    private class User {
        private String name;
        private Integer age;
    }
}
