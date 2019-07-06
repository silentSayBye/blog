package com.destiny.blog.domain.enums;

import com.destiny.blog.util.BaseEnum;

public enum HttpMethodEnum implements BaseEnum {

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

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
