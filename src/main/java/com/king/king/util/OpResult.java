package com.king.king.util;

/**
 * @author shihao.ma
 * @since 2019/12/31
 */
public interface OpResult {
    String HTTP_HEADER_ATTR = "el-result-code";
    OpResult OK = () -> {
        return "OK";
    };
    OpResult NG = () -> {
        return "NG";
    };

    String getCode();

    default String getDesc() {
        return this.getCode();
    }
}
