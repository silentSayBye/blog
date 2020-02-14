package com.destiny.blog.util;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        String securePwd = passwordEncoder.encode("sfdfadfa1");
        System.out.println(securePwd);
        String str = "$2a$10$G7KutihTWX0MHMcyzyXPH.Zr4r69dqqo9HpZ1AbPXg9M091QUj1WG";
        System.out.println(passwordEncoder.matches("sfdfadfa1",str));
    }



}