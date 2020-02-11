package com.destiny.blog.domain.vo;

import com.destiny.blog.domain.base.Result;
import com.destiny.blog.domain.enums.ResponseCode;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable, Result {
   private String code;
   private String message;
   private T data;


   public static <T> Response unauthorized (T data){
       return Response.bulider()
               .code(ResponseCode.UNAUTHORIZED.getCode())
               .message(ResponseCode.UNAUTHORIZED.getMessage())
               .data(data)
               .bulid();
   }

    public static <T> Response failed (T data){
        return Response.bulider()
                .code(ResponseCode.FAILED.getCode())
                .message(ResponseCode.FAILED.getMessage())
                .data(data)
                .bulid();
    }

    public static <T> Response success (T data){
        return Response.bulider()
                .code(ResponseCode.SUCCUSS.getCode())
                .message(ResponseCode.SUCCUSS.getMessage())
                .data(data)
                .bulid();
    }

    public static <T> Response forbidden (T data){
        return Response.bulider()
                .code(ResponseCode.FORBIDDEN.getCode())
                .message(ResponseCode.FORBIDDEN.getMessage())
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
