package com.blog.security.authority.setting;

import lombok.Data;
import org.apache.commons.compress.utils.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Date 2020/2/19 20:51
 * @Version 1.0
 **/
@Data
@ConfigurationProperties(prefix = "authority")
public class AuthoritySettings {

    private List<MybatisAuthoritySetting> mybatisAuthoritySettings = Lists.newArrayList();

    private List<HibernateAuthoritySetting> hibernateAuthoritySettings = Lists.newArrayList();


}
