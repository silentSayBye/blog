package com.destiny.api.domain.vo;


import com.destiny.api.domain.base.Result;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName DefaultErrorResult
 * @Author Administrator
 * @Date 2019/7/2018:06
 * @Version 1.0
 **/

@Data
@NoArgsConstructor
public class DefaultErrorResult implements Result, Serializable {

//    private Integer status;
//    private String error;
    private String message;
    private Integer code;
    private String path;
    private String exception;
//    private Object errors;
    private String timestamp;

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
//        private Integer status;
//        private String error;
        private String message;
        private Integer code;
        private String path;
        private String exception;
//        private Object errors;
        private String timestamp;

        public Builder(){}

//        public Builder status(Integer status){
//            this.status = status;
//            return this;
//        }
//
//        public Builder error(String error){
//            this.error = error;
//            return this;
//        }
        public Builder message(String message){
            this.message = message;
            return this;
        }

        public Builder code(Integer code){
            this.code = code;
            return  this;
        }

        public Builder path(String path){
            this.path = path;
            return this;
        }

        public Builder exception(String exception){
            this.exception = exception;
            return this;
        }

//        public Builder errors(Object errors){
//            this.errors = errors;
//            return this;
//        }

        public Builder timestamp(String timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public DefaultErrorResult build(){
            DefaultErrorResult defaultErrorResult = new DefaultErrorResult();
            defaultErrorResult.setCode(this.code);
            defaultErrorResult.setMessage(this.message);
//            defaultErrorResult.setError(this.error);
//            defaultErrorResult.setErrors(this.errors);
            defaultErrorResult.setException(this.exception);
            defaultErrorResult.setPath(this.path);
            defaultErrorResult.setTimestamp(this.timestamp);
//            defaultErrorResult.setStatus(this.status);
           return defaultErrorResult;
        }
    }

}
