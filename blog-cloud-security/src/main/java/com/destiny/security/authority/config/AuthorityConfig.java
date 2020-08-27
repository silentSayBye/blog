package com.destiny.security.authority.config;

import com.destiny.security.authority.interpreter.AuthorityInterpreter;
import com.destiny.security.authority.interpreter.HibernateAuthorityInterpreter;
import com.destiny.security.authority.interpreter.JDBCAuthorityInterpreter;
import com.destiny.security.authority.setting.AuthoritySetting;
import com.destiny.security.authority.setting.AuthoritySettings;
import com.destiny.security.authority.setting.HibernateAuthoritySetting;
import com.destiny.security.authority.setting.MybatisAuthoritySetting;
import com.destiny.security.authority.interpreter.AuthorityInterpreter;
import com.destiny.security.authority.interpreter.HibernateAuthorityInterpreter;
import com.destiny.security.authority.interpreter.JDBCAuthorityInterpreter;
import com.destiny.security.authority.setting.AuthoritySetting;
import com.destiny.security.authority.setting.AuthoritySettings;
import com.destiny.security.authority.setting.HibernateAuthoritySetting;
import com.destiny.security.authority.setting.MybatisAuthoritySetting;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @Date 2020/2/19 20:40
 * @Version 1.0
 **/

@Configuration
@Slf4j
@EnableConfigurationProperties(AuthoritySettings.class)
public class AuthorityConfig {

    @Bean
    public AuthorityInterpreter initAuthorityInterpreter(AuthoritySettings authoritySettings) {
        List<HibernateAuthoritySetting> hibernateAuthoritySettings = authoritySettings.getHibernateAuthoritySettings();
        List<MybatisAuthoritySetting> mybatisAuthoritySettings = authoritySettings.getMybatisAuthoritySettings();
        if (CollectionUtils.isNotEmpty(hibernateAuthoritySettings) && CollectionUtils.isNotEmpty(mybatisAuthoritySettings)){
            throw new IllegalStateException("Only support one config!");
        }
        AuthorityInterpreter authorityInterpreter = null;
        Map<String, AuthoritySetting> authoritySettingMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(hibernateAuthoritySettings)){
            authoritySettingMap.putAll(Maps.uniqueIndex(hibernateAuthoritySettings.iterator(),AuthoritySetting::getAuthorityCode));
            authorityInterpreter = new HibernateAuthorityInterpreter(authoritySettingMap);
        }else if (CollectionUtils.isNotEmpty(mybatisAuthoritySettings)){
            authoritySettingMap.putAll(Maps.uniqueIndex(mybatisAuthoritySettings.iterator(),AuthoritySetting::getAuthorityCode));
            authorityInterpreter = new JDBCAuthorityInterpreter(authoritySettingMap);
        }
        return  authorityInterpreter;
    }
}
