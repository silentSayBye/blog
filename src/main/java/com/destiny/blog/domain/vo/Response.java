package com.destiny.blog.domain.vo;

import com.destiny.blog.domain.base.Result;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable, Result {
   private String code;
   private String message;
   private T data;


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
