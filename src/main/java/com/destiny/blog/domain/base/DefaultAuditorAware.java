package com.destiny.blog.domain.base;

import com.destiny.blog.domain.dto.UserDto;
import com.destiny.blog.domain.pojo.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DefaultAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Optional<Object> principal  = Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal);
        String name = null;
        if (principal.isPresent()){
            Object username = principal.get();
            if (username instanceof String){
                name = (String) username;
            }else{
                throw new RuntimeException("SecurityContext中的principal应该存储用户名，当前存储" + username.getClass() + username.toString() );
            }
        }
        return Optional.ofNullable(name);
    }

    private UserDto createUserDto(String username) {
        User user = User.builder().username("admin").build();
        UserDto defaultUserDto = new UserDto();
        defaultUserDto.setUser(user);
        return defaultUserDto;
    }
}
