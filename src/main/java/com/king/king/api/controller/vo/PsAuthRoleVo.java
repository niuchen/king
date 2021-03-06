package com.king.king.api.controller.vo;

import com.king.king.util.ExcelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限角色(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author niuchen
 * @since 2019-12-25
 * PS_AUTH_ROLE
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsAuthRoleVo {
    /**
     * PS_AUTH_ROLE.ID
     */
    @ExcelField(title = "id")
    @ApiModelProperty(value = "")
    private Integer id;

    /**
     * 用户层级(P:平台级|T:租户级|E:企业级)
     * <p>
     * PS_AUTH_ROLE.LAYER
     */
    @ApiModelProperty(value = "用户层级(P:平台级|T:租户级|E:企业级)")
    private String layer;

    /**
     * 用户领域(PA:平台管理方|TA:租户管理方|EO:企业运营方|EP:企业合作方)
     * <p>
     * PS_AUTH_ROLE.FIELD
     */
    @ApiModelProperty(value = "用户领域(PA:平台管理方|TA:租户管理方|EO:企业运营方|EP:企业合作方)")
    private String field;

    /**
     * 名称
     * <p>
     * PS_AUTH_ROLE.NAME
     */
    @ExcelField(title = "名称")
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * PS_AUTH_ROLE.DELETE_FLAG
     */
    @ApiModelProperty(value = "")
    private Boolean deleteFlag;
}