package com.destiny.blog.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class CustomException extends RuntimeException{
    private String message;
    private Throwable cause;
    private HttpStatus status;

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

    public CustomException(String message, HttpStatus status){
        this.message = message;
        this.status = status;
    }
}
