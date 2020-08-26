package com.destiny.api.config.http;

import com.destiny.api.domain.base.Result;
import com.destiny.api.domain.vo.DefaultErrorResult;
import com.destiny.api.domain.vo.Response;
import com.destiny.api.util.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Class<?> responseClazz = returnType.getDeclaringClass();
        if (responseClazz.isAssignableFrom(Result.class)){
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
