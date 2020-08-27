package com.destiny.security.enums;

/**
 * @ClassName ResponseStatus
 * @Author Administrator
 * @Date 2019/7/1822:46
 * @Version 1.0
 **/
public enum  ResponseStatus {

    SUCCUSS("200","Success"),
    FORBIDDEN("403","Forbidden"),
    FAILED("500","Internal Server Error"),
    VALIDATE_FAILED("400","请求参数错误"),
    UNAUTHORIZED("401","暂未登录或token已经过期"),
    OPERATOION_FREQUENCY_TOO_FAST("1000","操作过快,请稍后再试!");

    private  String code;

    private  String message;

     ResponseStatus(String code,String message){
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
