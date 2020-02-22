package com.destiny.api.domain.base;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

        if (principal.isPresent() && principal.get() instanceof UserDetails){
            UserDetails userDetails = (UserDetails)principal.get();
            name = userDetails.getUsername();
        }
        return Optional.ofNullable(name);
    }
}
