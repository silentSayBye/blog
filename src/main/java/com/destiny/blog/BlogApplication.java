package com.destiny.blog;

import com.destiny.blog.config.security.CustomSecurityMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@EnableJpaAuditing
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args)
        .getBean(CustomSecurityMetadata.class)
        .initRequestMap();
    }

}
