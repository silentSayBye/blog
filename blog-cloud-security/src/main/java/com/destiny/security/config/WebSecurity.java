package com.destiny.security.config;


import com.blog.security.entity.Authority;
import com.blog.security.entity.Response;
import com.blog.security.entity.User;
import com.blog.security.entity.UserDto;
import com.blog.security.exception.FailFetchInfoByFeignException;
import com.blog.security.filter.JwtAuthenticationTokenFilter;
import com.blog.security.filter.RestAuthenticationEntryPoint;
import com.blog.security.filter.RestfulAccessDeniedHandler;
import com.blog.security.metadata.CustomSecurityMetadata;
import com.blog.security.service.UserRemoteService;
import com.blog.security.voter.FullyMatchRoleVoter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Set;


/**
 * @Author Administrator
 * @Date 2019/7/24 21:00
 * @Version 1.0
 **/

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRemoteService userRepository;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        //不需要保护的资源路径允许访问
        for (String url : ignoreUrlsConfig().getUrls()) {
            registry.antMatchers(url).permitAll();
        }
        //允许跨域请求的OPTIONS请求
        registry.antMatchers(HttpMethod.OPTIONS)
                .permitAll();
        registry.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // 关闭跨站请求防护及不使用session
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler())
                .authenticationEntryPoint(restAuthenticationEntryPoint())
                // 自定义权限拦截器JWT过滤器
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig(){
        return new IgnoreUrlsConfig();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterInvocationSecurityMetadataSource securityMetadata(){
        return new CustomSecurityMetadata();
    }

    @Bean
    public FullyMatchRoleVoter fullyMatchRoleVoter(){
        return new FullyMatchRoleVoter();
    }

    @Bean
    public AuthenticatedVoter authenticatedVoter() {
        return new AuthenticatedVoter();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager(){
        List<AccessDecisionVoter<? extends Object>> voters = Lists.newArrayList();
        voters.add(fullyMatchRoleVoter());
        voters.add(authenticatedVoter());
        AffirmativeBased accessDecisionManager = new AffirmativeBased(voters);
        return accessDecisionManager;
    }

    @Bean
    public FilterSecurityInterceptor  filterSecurityInterceptor(){
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(securityMetadata());
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager());
        return filterSecurityInterceptor;
    }


    @Bean
    public RestAuthenticationEntryPoint  restAuthenticationEntryPoint(){
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public RestfulAccessDeniedHandler  restfulAccessDeniedHandler(){
        return new RestfulAccessDeniedHandler();
    }

    @Bean
    public JwtAuthenticationTokenFilter  jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public UserDetailsService userDetailsService (){
        return username ->{
            Assert.notNull(username,"用户名不能为空！");
            Response userResponse = userRepository.findByUsername(username);
            if (userResponse == null){
                throw new FailFetchInfoByFeignException("获取用户信息失败");
            }else {
                User user = (User)userResponse.getData();
                if (user != null){
                    Response authorityResponse = userRepository.findAuthorityByUsername(username);
                    if (authorityResponse == null){
                        throw new FailFetchInfoByFeignException("获取用户权限失败");
                    }
                    Set<Authority> authorities = (Set<Authority>)authorityResponse.getData();
                    return new UserDto(user, authorities);
                }
                throw new UsernameNotFoundException("该用户不存在");
            }
        };
    }
}
