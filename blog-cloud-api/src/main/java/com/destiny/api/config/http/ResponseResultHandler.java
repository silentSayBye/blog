package com.destiny.api.config.http;

import com.destiny.api.config.annotation.ResponseResult;
import com.destiny.api.config.interceptor.ResponseInterceptor;
import com.destiny.api.domain.base.Result;
import com.destiny.api.domain.vo.DefaultErrorResult;
import com.destiny.api.domain.vo.Response;
import com.destiny.api.util.JsonUtil;
import com.destiny.api.util.RequestContextUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ResponseResultHandler
 * @Author Administrator
 * @Date 2019/7/2017:37
 * @Version 1.0
 **/

@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        HttpServletRequest request  = RequestContextUtil.getRequest();
        ResponseResult responseResult = (ResponseResult)request.getAttribute(ResponseInterceptor.RESPONSERESULT);
        return responseResult != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ResponseResult responseResult = (ResponseResult)RequestContextUtil.getRequest().getAttribute(ResponseInterceptor.RESPONSERESULT);
        Class value = responseResult.value();
        if (value.isAssignableFrom(Result.class)){
            if (body instanceof DefaultErrorResult){
                DefaultErrorResult defaultErrorResult = (DefaultErrorResult)body;
                return Response.bulider().code(defaultErrorResult.getCode().toString())
                        .message(defaultErrorResult.getMessage())
                        .data(defaultErrorResult.getMessage())
                        .bulid();
            }else if (body instanceof String){
                return JsonUtil.objectToJson(Response.success(body));
            }
            return JsonUtil.objectToJson(Response.success(body));
        }
       return body;
    }
}