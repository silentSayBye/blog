package com.destiny.blog.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @ClassName JwtVo
 * @Author Administrator
 * @Date 2019/6/2420:59
 * @Version 1.0
 **/
@Setter
@Getter
public class JwtVo {
    private HttpStatus code;
    private Object data;
    private String message;
    private String token;
}
