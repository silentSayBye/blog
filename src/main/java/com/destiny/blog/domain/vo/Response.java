package com.destiny.blog.domain.vo;

public class Response<T> {

   private String code;
   private String message;
   private T data;

   private Response(Bulider<T> bulider){
       this.code = bulider.code;
       this.message = bulider.message;
       this.data =  bulider.data;
   }
    public static final class Bulider<T>{

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
        public Bulider<T> ata(T data){
            this.data = data;
            return this;
        }

        public Response<T> bulider(){
            return new Response<>(this);
        }
    }

}
