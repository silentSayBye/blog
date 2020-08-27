package com.destiny.security.authority.exception;

/**
 * @Date 2020/2/20 18:44
 * @Version 1.0
 **/
public class InvalidAuthorityCodeException extends RuntimeException {
    private String invalidCode;

    private static final String MESSAGE = "invalid authority code is %s";
    public InvalidAuthorityCodeException(String invalidCode){
       this.invalidCode = invalidCode;
    }

    @Override
    public String getMessage() {
        return String.format("MESSAGE",invalidCode);
    }
}
