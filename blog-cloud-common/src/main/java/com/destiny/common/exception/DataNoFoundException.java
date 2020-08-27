package com.destiny.common.exception;

public class DataNoFoundException extends RuntimeException {
    private String message;
    private String code;

    public DataNoFoundException(String code, String message) {
        super(message);
        this.code = code;
    }
}
