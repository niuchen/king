package com.king.king.api.enty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色赋权(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author niuchen
 * @since 2019-12-25
 * PS_AUTH_USER_ROLE
 */
//@ApiModel(value = "角色赋权(实施阶段配置、运营阶段亦可维护、外部审计)")
@Data
public class PsAuthUserRole {
    /**
     * 用户ID
     * <p>
     * PS_AUTH_USER_ROLE.USER_ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 权限角色ID
     * <p>
     * PS_AUTH_USER_ROLE.ROLE_ID
     */
    @ApiModelProperty(value = "权限角色ID")
    private Long roleId;
}