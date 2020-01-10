package com.king.king.api.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色赋权(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author niuchen
 * @since 2019-12-25
 * PS_AUTH_ROLE_PERM
 */
@ApiModel(value = "角色赋权(实施阶段配置、运营阶段亦可维护、外部审计)")
@Data
public class PsAuthRolePermVo {
    /**
     * 权限角色ID
     * <p>
     * PS_AUTH_ROLE_PERM.ROLE_ID
     */
    @ApiModelProperty(value = "权限角色ID")
    private Long roleId;

    /**
     * 权限编码
     * <p>
     * PS_AUTH_ROLE_PERM.PERM_CODE
     */
    @ApiModelProperty(value = "权限编码")
    private String permCode;
}