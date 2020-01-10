package com.king.king.cig.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.king.king.api.controller.domain.EdpUser;
import com.king.king.api.controller.vo.PermssionsUserOjb;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.Collections;
import java.util.Set;

/**
 * @author neo.pan
 * @since 17/7/31
 */
@Value(staticConstructor = "of")
@ToString(callSuper = true)
@EqualsAndHashCode(of = "user", callSuper = false)
public class EdpAuthPrincipal extends AuthPrincipal {
    private static final long serialVersionUID = -2840948939861297756L;
    @JsonIgnore
    @Override
    public Object getId() {
        return user.getId();
    }

    /**
     * @return 用户ID
     */
    public Long userId() {
        return user.getId();
    }

    /**
     * 用户信息
     */
    private final EdpUser user;

    /**
     * 权限角色
     */
    private final Set<String> roles;

    //20191009niuchen 数据权限功能增加的三个权限集合
    private final PermssionsUserOjb permssionsUserOjb;


    /**
     * @param role 角色
     * @return 是否拥有？
     */
    public boolean hasRole(String role) {
        return roles.contains(role);
    }

    EdpAuthPrincipal(String realm, EdpUser user, Set<String> roles, PermssionsUserOjb permssionsUserOjb) {
        super(realm);
        this.user = user;
        this.roles = Collections.unmodifiableSet(roles);
        this.permssionsUserOjb=permssionsUserOjb;
     }

}
