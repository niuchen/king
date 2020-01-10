package com.king.king.cig.security;

import java.io.Serializable;
import java.security.Principal;
import java.util.Set;

/**
 * @author shihao.ma
 * @since 2019/12/31
 */
public abstract class AuthPrincipal implements Principal, Serializable {
    private final String realm;
    // TODO 引入shiro 的jar包 打开注释的代码
//    private AuthorizationInfo authz;

    public abstract Object getId();

    public String getName() {
        return String.valueOf(this.getId());
    }

    protected AuthPrincipal(String realm) {
        this.realm = realm;
    }

    public abstract Set<String> getRoles();

    // TODO 引入shiro 的jar包 打开注释的代码
//    public final AuthorizationInfo toAuthorizationInfo() {
//        if (this.authz == null) {
//            this.authz = new SimpleAuthorizationInfo(this.getRoles());
//        }
//
//        return this.authz;
//    }

    public String getRealm() {
        return this.realm;
    }
}
