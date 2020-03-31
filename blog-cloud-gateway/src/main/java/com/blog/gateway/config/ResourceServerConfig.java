//package com.blog.gateway.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//
///**
// * @Date 2020/3/9 23:40
// * @Version 1.0
// **/
//@Configuration
//@EnableResourceServer
//@Order(SecurityProperties.DEFAULT_FILTER_ORDER - 10)
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//    @Autowired
//    private TokenStore tokenStore;
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.stateless(false)
//                .tokenStore(tokenStore);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.requestMatchers()
//                .antMatchers("/**/oauth/**")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**/oauth/**")
//                .authenticated();
//    }
//
////    @Bean
////    public RedisTokenStore tokenStore(){
////        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
////        redisTokenStore.setPrefix("auth-token");
////        return redisTokenStore;
////    }
//}
