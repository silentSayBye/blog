package com.blog.gateway.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.Filter;
import java.text.SimpleDateFormat;

/**
 * @Date 2020/3/15 17:33
 * @Version 1.0
 **/
@Configuration
public class GatewayWebConfig {

    @Bean
    public MappingJackson2HttpMessageConverter converter(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.enable(JsonParser.Feature.ALLOW_MISSING_VALUES);
        objectMapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    // 文件解析器
    @Bean
    public StandardServletMultipartResolver getStandardServletMultipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
