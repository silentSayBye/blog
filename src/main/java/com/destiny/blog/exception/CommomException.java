package com.destiny.blog.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * @ClassName CommomException
 * @Author Administrator
 * @Date 2019/6/1822:39
 * @Version 1.0
 **/
@Getter
@ToString
public class CommomException extends RuntimeException {

    private String mesasage;

    private Throwable cause;

    public CommomException(String message, Throwable cause){
        this.mesasage = message;
        this.cause = cause;
    }

    public CommomException(){}

    public  CommomException(String mesasage){
        this.mesasage = mesasage;
    }
}
