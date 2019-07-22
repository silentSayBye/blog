package com.destiny.blog.config.interceptor;

import com.destiny.blog.config.annotation.ResponseResult;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @ClassName ResponseInterceptor
 * @Author Administrator
 * @Date 2019/7/2015:04
 * @Version 1.0
 **/

@Component
public class ResponseInterceptor implements HandlerInterceptor {

    public static final String RESPONSERESULT = "response-result";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            final HandlerMethod handlerMethod = (HandlerMethod)handler;
            Method method = handlerMethod.getMethod();
            Class<?> beanType = handlerMethod.getBeanType();
            if (method.isAnnotationPresent(ResponseResult.class)){
                request.setAttribute(RESPONSERESULT,beanType.getAnnotation(ResponseResult.class));
            }else if (beanType.isAnnotationPresent(ResponseResult.class)){
                request.setAttribute(RESPONSERESULT,beanType.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}
