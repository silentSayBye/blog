package com.destiny.security.enums;


public enum ResourceType {

    FUNCTION(1,"功能"),

    MENU(2,"菜单");

    private int value;

    private String description;

    ResourceType(int value,String description){
        this.value=value;
        this.description=description;
    }


    public int getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }
}
