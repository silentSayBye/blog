package com.destiny.blog.domain.enums;

/**
 * @ClassName ResponseCode
 * @Author Administrator
 * @Date 2019/7/1822:46
 * @Version 1.0
 **/
public enum  ResponseCode {

    C200("200","Success"),
    C403("403","Forbidden"),
    C500("500","Internal Server Error"),
    C400("400","请求参数错误"),
    C402("-420","用户未登录"),
    OPERATOION_FREQUENCY_TOO_FAST("1000","操作过快,请稍后再试!");

    private  String code;

    private  String message;

    private ResponseCode(String code,String message){
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
