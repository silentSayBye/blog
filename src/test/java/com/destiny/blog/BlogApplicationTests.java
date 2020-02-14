package com.destiny.blog;

import com.destiny.blog.dao.ResourceRepository;
import com.destiny.blog.dao.RoleRepository;
import com.destiny.blog.dao.UserRepository;
import com.destiny.blog.domain.pojo.User;
import com.destiny.blog.service.ArticleCategoryService;
import com.destiny.blog.service.UserService;
import com.destiny.blog.service.impl.ArticleCategoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {


    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ArticleCategoryServiceImpl articleCategoryService;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoads() {

        System.out.println(applicationContext.getBeanNamesForType(UserService.class));

//        try{
//            user = userRepository.findPasswordByUsernameOrEmail("zhangsan", "");
//        }catch (Exception e){
//            System.out.println("user error");
//        }
//
//        System.out.println(user);
    }

}
