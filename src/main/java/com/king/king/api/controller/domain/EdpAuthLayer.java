package com.king.king.api.controller.domain;

import lombok.Getter;

/**
 * @author neo.pan
 * @since 2018/03/23
 */
public enum EdpAuthLayer {

    P("平台级"), T("租户级"), E("企业级"),;

    public String getCode() {
        return name();
    }

    @Getter
    private String name;

    EdpAuthLayer(String name) {
        this.name = name;
    }
}
