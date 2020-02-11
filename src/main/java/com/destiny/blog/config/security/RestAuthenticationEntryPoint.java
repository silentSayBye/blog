package com.destiny.blog.config.security;

import com.alibaba.fastjson.JSON;
import com.destiny.blog.domain.vo.Response;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果@ClassName RestAuthenticationEntryPoint

 **/
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
       response.setCharacterEncoding("UTF-8");
       response.setContentType("application/json");
       response.getWriter().println(JSON.toJSON(Response.unauthorized(e.getMessage())));
       response.getWriter().flush();
    }
}