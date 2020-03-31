package com.blog.gateway.exception;

import lombok.Data;

/**
 * @Date 2020/3/21 20:34
 * @Version 1.0
 **/
@Data
public class CustomException extends RuntimeException {

    private String message;

    public CustomException(String message){
        super(message);
    }
}
