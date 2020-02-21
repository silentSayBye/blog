package com.destiny.blog.config.security;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

/**
 * @Date 2020/2/15 18:13
 * @Version 1.0
 **/
@Slf4j
public class FullyMatchRoleVoter implements AccessDecisionVoter<Object> {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Set<String> authorityCode = Sets.newHashSet();
        authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .forEach(authorityCode::add);
            for (ConfigAttribute attribute:attributes) {
            if (!authorityCode.contains(attribute.getAttribute())){
                log.info("ACCESS_DENIED");
                return ACCESS_DENIED;
            }
        }
        log.info("ACCESS_GRANTED");
        return ACCESS_GRANTED;
    }
}
