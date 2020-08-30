package com.destiny.api.exception;

/**
 * @Date 2020/8/30 2:03
 * @Version 1.0
 **/
public class UserNotExistException extends RuntimeException {

    private Integer code;

    private String message;

    public UserNotExistException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
