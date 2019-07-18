package com.destiny.blog.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomException extends RuntimeException{
    private String message;
    private Throwable cause;

    public CustomException(){
        super();
    }
    public CustomException(String message,Throwable cause){
        this.message = message;
        this.cause = cause;
    }
    public CustomException(String message){
        this.message = message;
    }
}
