package com.destiny.api.domain.enums;

import com.destiny.api.util.BaseEnum;

public enum ResourceType implements BaseEnum {

    FUNCTION(1,"功能"),

    MENU(2,"菜单");

    private int value;

    private String description;

    ResourceType(int value,String description){
        this.value=value;
        this.description=description;
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
