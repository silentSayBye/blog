package com.blog.security.filter;

import com.alibaba.fastjson.JSON;
import com.blog.security.entity.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果@ClassName RestAuthenticationEntryPoint
 **/
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
       response.setCharacterEncoding("UTF-8");
       response.setContentType("application/json");
       response.setStatus(HttpStatus.FORBIDDEN.value());
       response.getWriter().println(JSON.toJSON(Response.unauthorized(e.getMessage())));
       response.getWriter().flush();
    }
}
