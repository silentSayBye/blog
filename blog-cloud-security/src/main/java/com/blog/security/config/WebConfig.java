package com.blog.security.config;

import com.blog.security.userDetails.CustomPreAuthenticationChecks;
import com.blog.security.userDetails.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

/**
 * @Date 2020/3/11 21:43
 * @Version 1.0
 **/
@Configuration
public class WebConfig {

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        ProviderManager authenticationManager = new ProviderManager(
                Arrays.asList(authenticationProvider()));
        //不擦除认证密码，擦除会导致TokenBasedRememberMeServices因为找不到Credentials再调用UserDetailsService而抛出UsernameNotFoundException
        authenticationManager.setEraseCredentialsAfterAuthentication(false);
        return authenticationManager;
    }
    @Bean
    public CustomPreAuthenticationChecks initPreAuthenticationChecks() {
        return new CustomPreAuthenticationChecks();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customerUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        daoAuthenticationProvider.setPreAuthenticationChecks(initPreAuthenticationChecks());
        return daoAuthenticationProvider;
    }
}
