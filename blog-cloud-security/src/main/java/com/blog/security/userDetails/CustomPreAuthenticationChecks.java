package com.blog.security.userDetails;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * @Date 2020/3/11 22:40
 * @Version 1.0
 **/
@Slf4j
public class CustomPreAuthenticationChecks implements UserDetailsChecker {

    public void check(UserDetails user) {
        if (!user.isAccountNonLocked()) {
            log.debug("User account is locked");
            throw new LockedException("User account is locked");
        }

        if (!user.isEnabled()) {
            log.debug("User account is disabled");
            throw new DisabledException("User is disabled");
        }

        if (!user.isAccountNonExpired()) {
            log.debug("User account is expired");
            throw new AccountExpiredException("User account has expired");
        }

    }
}
