package com.destiny.api.exception;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
public class CustomException extends RuntimeException{
    private String message;
    private HttpStatus status;

    public CustomException(){
        super();
    }

    public CustomException(String message){
        this.message = message;
    }

    public CustomException(String message,HttpStatus status){
        this.message = message;
        this.status = status;
    }


}
