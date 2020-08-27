package com.destiny.security.entity;

import com.destiny.security.enums.ResponseCode;
import com.destiny.security.enums.ResponseStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable{
   private String code;
   private String message;
   private T data;


   public static <T> Response unauthorized (T data){
       return Response.bulider()
               .code(ResponseCode.FAILED.getCode())
               .message(ResponseStatus.UNAUTHORIZED.getMessage())
               .data(data)
               .bulid();
   }

    public static <T> Response failed (T data){
        return Response.bulider()
                .code(ResponseCode.FAILED.getCode())
                .message(ResponseStatus.FAILED.getMessage())
                .data(data)
                .bulid();
    }

    public static <T> Response success (T data){
        return Response.bulider()
                .code(ResponseCode.SUCCUSS.getCode())
                .message(ResponseStatus.SUCCUSS.getMessage())
                .data(data)
                .bulid();
    }

    public static <T> Response forbidden (T data){
        return Response.bulider()
                .code(ResponseCode.FAILED.getCode())
                .message(ResponseStatus.FORBIDDEN.getMessage())
                .data(data)
                .bulid();
    }

   public static <T> Bulider<T> bulider(){
       return new Bulider<T>();
   }
   public static class Bulider<T>{

        private String code;
        private String message;
        private T data;

        public Bulider(){}
        public Bulider<T> code(String code){
            this.code = code;
            return this;
        }

        public Bulider<T> message(String message){
            this.message = message;
            return this;
        }
        public Bulider<T> data(T data){
            this.data = data;
            return this;
        }

        public  Response<T> bulid(){
            Response<T> response = new Response<>();
            response.setMessage(this.message);
            response.setCode(this.code);
            response.setData(this.data);
            return response;
        }
    }
}
