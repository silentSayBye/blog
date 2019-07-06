package com.destiny.blog.domain.dto;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @ClassName JwtResult
 * @Author Administrator
 * @Date 2019/6/2421:01
 * @Version 1.0
 **/

@Builder
public class JwtResult {

    private Claims claims;
    private boolean result;
    private String message;
    private HttpStatus code;
}
