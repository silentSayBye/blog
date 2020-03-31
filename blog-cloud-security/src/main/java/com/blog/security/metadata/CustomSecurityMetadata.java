package com.blog.security.metadata;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blog.security.entity.Authority;
import com.blog.security.entity.Resource;
import com.blog.security.entity.Response;
import com.blog.security.exception.FailFetchInfoByFeignException;
import com.blog.security.service.UserRemoteService;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Date 2020/2/15 17:09
 * @Version 1.0
 **/

@Slf4j
@Component
public class CustomSecurityMetadata implements FilterInvocationSecurityMetadataSource {

    private Map<RequestMatcher,Collection<ConfigAttribute>> requestMap = Maps.newLinkedHashMap();

    @Autowired
    private UserRemoteService userRemoteService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Set<ConfigAttribute> configAttributes = Sets.newHashSet();
        if (requestMap.isEmpty()){
            return configAttributes;
        }
        HttpServletRequest httpRequest = ((FilterInvocation) object).getHttpRequest();
        for (Map.Entry<RequestMatcher,Collection<ConfigAttribute>> request :requestMap.entrySet()){
            RequestMatcher matcher = request.getKey();
            if (matcher.matches(httpRequest)){
                configAttributes.addAll(request.getValue());
            }
        }
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        if (requestMap.isEmpty()){
            return null;
        }
        // 是否可以换成set
        Collection<ConfigAttribute> list = new ArrayList<>();
        requestMap.values()
                .forEach(list::addAll);
        return list;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    public void initRequestMap(){
        Response response = userRemoteService.findAllResources();
        if (response == null){
            throw new FailFetchInfoByFeignException("获取资源信息失败");
        }else{
            List responseData = (List)response.getData();
            String jsonString = JSON.toJSONString(responseData);
            List<Resource> resources = JSONObject.parseArray(jsonString, Resource.class);
            resources.forEach(resource -> {
                List<Authority> authorities = resource.getAuthorityList();
                if (resource.getUrl() != null){
                    RequestMatcher requestMatcher = new AntPathRequestMatcher(resource.getUrl());
                    List<ConfigAttribute> list = SecurityConfig.createList(getAuthorityCode(authorities));
                    requestMap.put(requestMatcher,list);
                }
            });
        }
    }

    private String[] getAuthorityCode( List<Authority> authorities){
        List<String> list = authorities.stream().map(Authority::getCode)
                .collect(Collectors.toList());
        String[] arr = new String[list.size()];
        return list.toArray(arr);
    }
}
