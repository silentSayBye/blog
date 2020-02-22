package com.destiny.api.config.security;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @ClassName IgnoreUrlsConfig
 * @Author Administrator
 * @Date 2019/12/3022:32
 * @Version 1.0
 **/
@Setter
@Getter
@ConfigurationProperties(prefix = "secure.ignore")
public class IgnoreUrlsConfig {
    private List<String> urls = Lists.newArrayList();
}
