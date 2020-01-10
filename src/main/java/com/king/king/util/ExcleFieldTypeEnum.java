package com.king.king.util;

public enum ExcleFieldTypeEnum {
    DEFAULT("默认格式","DEFAULT"),
    MONEY("金额钱格式","MONEY"),
    DATE("日期格式","DATE"),
    PERCENTAGE("百分比格式","PERCENTAGE");


    private String code;
    private String name;
    ExcleFieldTypeEnum(String name, String code) {
        this.name=name;
        this.code = code;
    }

//    public String getCode(){
//        return code;
//    }
//    public String getName(){
//        return name;
//    }
}
