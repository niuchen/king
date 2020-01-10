package com.king.king.util;


import lombok.Getter;

/**shihao.ma 2019/12/31 猫哆哩迁移**/
public enum EdpCrudOp implements OpResult {
    NG_VALIDATION("数据校验错"),
    NG_NOT_EXISTS("数据不存在"),
    NG_KEY_EXISTS("数据已存在"),
    NG_CREATE("创建失败"),
    NG_UPDATE("更新失败"),
    NG_DELETE("删除失败"),
    NG_DELETE_BUILT_IN("应用內建数据不能被删除"),
    NG_DELETE_IN_USE("使用中的配置不能被删除");



    @Getter
    private final String desc;

    @Override
    public String getCode() {
        return name();
    }

    EdpCrudOp(String desc) {
        this.desc = desc;
    }


}
