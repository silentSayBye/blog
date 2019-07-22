package com.destiny.blog.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName RequestContextUtil
 * @Author Administrator
 * @Date 2019/7/2017:39
 * @Version 1.0
 **/
public class RequestContextUtil {

    public static ServletRequestAttributes getHttpRequest(){
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static HttpServletRequest getRequest(){
        return getHttpRequest().getRequest();
    }

    public static HttpServletResponse getResponse(){
        return getHttpRequest().getResponse();
    }

    public static ServletContext getServletContent(){
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }
}
