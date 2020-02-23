package com.blog.security.enums;

/**
 * @ClassName ResponseCode
 * @Author Administrator
 * @Date 2019/7/1822:46
 * @Version 1.0
 **/
public enum ResponseCode {

    SUCCUSS("1","Success"),
    FAILED("0","fail");

    private  String code;

    private  String message;

     ResponseCode(String code, String message){
        this.code = code;
        this.message = message;
     }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
