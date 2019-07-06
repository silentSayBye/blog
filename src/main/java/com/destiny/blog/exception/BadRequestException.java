package com.destiny.blog.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BadRequestException extends RuntimeException{
    private String message;
    private Throwable cause;

    public BadRequestException(){
        super();
    }
    public BadRequestException(String message,Throwable cause){
        this.message = message;
        this.cause = cause;
    }
    public BadRequestException(String message){
        this.message = message;
    }
}
