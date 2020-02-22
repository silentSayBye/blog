package com.destiny.api.util;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = {"com.destiny.api.domain.pojo"})
@EnableJpaRepositories(basePackages = {"com.destiny.api.dao"})
@EnableTransactionManagement(proxyTargetClass = true)
public class JpaConfig {

    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
