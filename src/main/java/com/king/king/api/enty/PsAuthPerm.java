package com.king.king.api.enty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 权限树(租户无关、实施阶段配置、运营阶段不可维护
 *
 * @author niuchen
 * @since 2019-12-25
 * PS_AUTH_PERM
 */
 @ApiModel(value = "权限树(租户无关、实施阶段配置、运营阶段不可维护")
@Data
public class PsAuthPerm {
    /**
     * 编码
     * <p>
     * PS_AUTH_PERM.CODE
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 父级权限编码(越往上API匹配范围越大、优先度越低)
     * <p>
     * PS_AUTH_PERM.PCODE
     */
    @ApiModelProperty(value = "父级权限编码(越往上API匹配范围越大、优先度越低)")
    private String pcode;

    /**
     * 权限层级
     * <p>
     * PS_AUTH_PERM.LVL
     */
    @ApiModelProperty(value = "权限层级")
    private Byte lvl;

    /**
     * 同级显示顺序
     * <p>
     * PS_AUTH_PERM.SEQ
     */
    @ApiModelProperty(value = "同级显示顺序")
    private Short seq;

    /**
     * 用户层级(P:平台级|T:租户级|E:企业级)
     * <p>
     * PS_AUTH_PERM.LAYER
     */
    @ApiModelProperty(value = "用户层级(P:平台级|T:租户级|E:企业级)")
    private String layer;

    /**
     * 用户领域(PA:平台管理方|TA:租户管理方|EO:企业运营方|EP:企业合作方)
     * <p>
     * PS_AUTH_PERM.FIELD
     */
    @ApiModelProperty(value = "用户领域(PA:平台管理方|TA:租户管理方|EO:企业运营方|EP:企业合作方)")
    private String field;

    /**
     * 名称
     * <p>
     * PS_AUTH_PERM.NAME
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 类别(F:功能项|M:菜单项)
     * <p>
     * PS_AUTH_PERM.TYPE
     */
    @ApiModelProperty(value = "类别(F:功能项|M:菜单项)")
    private String type;

    /**
     * 权限控制的API前缀
     * <p>
     * PS_AUTH_PERM.APIS
     */
    @ApiModelProperty(value = "权限控制的API前缀")
    private String apis;

    /**
     * 菜单URL(菜单项有效)
     * <p>
     * PS_AUTH_PERM.MENU_URL
     */
    @ApiModelProperty(value = "菜单URL(菜单项有效)")
    private String menuUrl;

    /**
     * 菜单URL(菜单项有效)
     * <p>
     * PS_AUTH_PERM.MENU_ICO
     */
    @ApiModelProperty(value = "菜单URL(菜单项有效)")
    private String menuIco;
}