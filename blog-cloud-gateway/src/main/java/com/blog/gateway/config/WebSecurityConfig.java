package com.blog.gateway.config;


import com.blog.security.config.IgnoreUrlsConfig;
import com.blog.security.filter.JwtAuthenticationTokenFilter;
import com.blog.security.filter.RestAuthenticationEntryPoint;
import com.blog.security.filter.RestfulAccessDeniedHandler;
import com.blog.security.metadata.CustomSecurityMetadata;
import com.blog.security.voter.FullyMatchRoleVoter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;


/**
 * @Author Administrator
 * @Date 2019/7/24 21:00
 * @Version 1.0
 **/

@AutoConfigureAfter(RibbonAutoConfiguration.class)
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(IgnoreUrlsConfig.class)
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private FullyMatchRoleVoter fullyMatchRoleVoter;

    @Autowired
    private CustomSecurityMetadata customSecurityMetadata;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
//                .authorizeRequests();

        // 不需要保护的资源路径允许访问
//        for (String url : ignoreUrlsConfig.getUrls()) {
//            registry.antMatchers(url).permitAll();
//        }

//        允许跨域请求的OPTIONS请求
//        registry.antMatchers(HttpMethod.OPTIONS)
//                .permitAll();
//        registry.and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                // 关闭跨站请求防护及不使用session
//                .and()
//                .csrf()
//                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                // 自定义权限拒绝处理类
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler)
////                .authenticationEntryPoint(restAuthenticationEntryPoint)
//                // 自定义权限拦截器JWT过滤器
//                .and()
//                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class);
        http.requestMatchers().antMatchers("/login")
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/**")
                .authenticated()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class);

    }

    @Bean
    public AccessDecisionManager accessDecisionManager(){
        List<AccessDecisionVoter<? extends Object>> voters = Lists.newArrayList();
        voters.add(fullyMatchRoleVoter);
        voters.add(authenticatedVoter());
        AffirmativeBased accessDecisionManager = new AffirmativeBased(voters);
        return accessDecisionManager;
    }

    @Bean
    public FilterSecurityInterceptor  filterSecurityInterceptor(){
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(customSecurityMetadata);
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager());
        return filterSecurityInterceptor;
    }

    @Bean
    public AuthenticatedVoter authenticatedVoter(){
        return new AuthenticatedVoter();
    }



//    @Bean
//    public JwtAuthenticationTokenFilter  jwtAuthenticationTokenFilter(){
//        return new JwtAuthenticationTokenFilter();
//    }

}
