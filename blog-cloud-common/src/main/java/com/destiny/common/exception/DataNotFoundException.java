package com.destiny.common.exception;

public class DataNotFoundException extends RuntimeException {
    private String message;
    private String code;

    public DataNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }
}
