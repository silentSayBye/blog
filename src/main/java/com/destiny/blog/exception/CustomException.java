package com.destiny.blog.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomException extends RuntimeException{
    private String message;

    public CustomException(){
        super();
    }

    public CustomException(String message){
        this.message = message;
    }


}
