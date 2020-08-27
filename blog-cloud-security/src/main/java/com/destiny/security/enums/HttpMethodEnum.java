package com.destiny.security.enums;


public enum HttpMethodEnum {

    POST(1,"post"),
    GET(2,"get"),
    PATCH(3,"patch"),
    DELETE(4,"delete"),
    PUT(5,"put");

    private int value;

    private String description;

    HttpMethodEnum(int value,String description){
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }
}
