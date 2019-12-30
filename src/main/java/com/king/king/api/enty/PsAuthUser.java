package com.king.king.api.enty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统用户(实施阶段配置平台管理员、运营阶段除了改密码外不可维护)
 *
 * @author niuchen
 * @since 2019-12-25
 * PS_AUTH_USER
 */
//@ApiModel(value = "系统用户(实施阶段配置平台管理员、运营阶段除了改密码外不可维护)")
@Data
public class PsAuthUser {
    /**
     * PS_AUTH_USER.ID
     */
    //@ApiModelProperty(value = "")
    private Integer id;

    /**
     * 用户层级(P:平台级|T:租户级|E:企业级)
     * <p>
     * PS_AUTH_USER.LAYER
     */
    //@ApiModelProperty(value = "用户层级(P:平台级|T:租户级|E:企业级)")
    private String layer;

    /**
     * 用户领域(PA:平台管理方|TA:租户管理方|EO:企业运营方|EP:企业合作方)
     * <p>
     * PS_AUTH_USER.FIELD
     */
    //@ApiModelProperty(value = "用户领域(PA:平台管理方|TA:租户管理方|EO:企业运营方|EP:企业合作方)")
    private String field;

    /**
     * 企业ID
     * <p>
     * PS_AUTH_USER.ENT_ID
     */
    //@ApiModelProperty(value = "企业ID")
    private Long entId;

    /**
     * 企业号
     * <p>
     * PS_AUTH_USER.ENT_CODE
     */
    //@ApiModelProperty(value = "企业号")
    private String entCode;

    /**
     * 企业地址号
     * <p>
     * PS_AUTH_USER.ENT_ADDR
     */
    //@ApiModelProperty(value = "企业地址号")
    private Long entAddr;

    /**
     * 员工ID
     * <p>
     * PS_AUTH_USER.EMP_ID
     */
    //@ApiModelProperty(value = "员工ID")
    private Long empId;

    /**
     * 员工号(即所属企业内用户名)
     * <p>
     * PS_AUTH_USER.EMP_CODE
     */
    //@ApiModelProperty(value = "员工号(即所属企业内用户名)")
    private String empCode;

    /**
     * 员工地址号
     * <p>
     * PS_AUTH_USER.EMP_ADDR
     */
    //@ApiModelProperty(value = "员工地址号")
    private Long empAddr;

    /**
     * 登录密码(SHA256+盐、通过密码找回获得初始密码)
     * <p>
     * PS_AUTH_USER.PASSWORD
     */
    //@ApiModelProperty(value = "登录密码(SHA256+盐、通过密码找回获得初始密码)")
    private String password;

    /**
     * 登录密码的盐
     * <p>
     * PS_AUTH_USER.SALT
     */
    //@ApiModelProperty(value = "登录密码的盐")
    private String salt;

    /**
     * 名称(用于显示)
     * <p>
     * PS_AUTH_USER.NAME
     */
    //@ApiModelProperty(value = "名称(用于显示)")
    private String name;

    /**
     * 邮箱
     * <p>
     * PS_AUTH_USER.EMAIL
     */
    //@ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机
     * <p>
     * PS_AUTH_USER.PHONE
     */
    //@ApiModelProperty(value = "手机")
    private String phone;

    /**
     * PS_AUTH_USER.DELETE_FLAG
     */
    //@ApiModelProperty(value = "")
    private Boolean deleteFlag;

    /**
     * 企业类型
     * <p>
     * PS_AUTH_USER.ENT_TYPE
     */
    //@ApiModelProperty(value = "企业类型")
    private String entType;

    /**
     * PS_AUTH_USER.LOGIN_NAME
     */
    //@ApiModelProperty(value = "")
    private String loginName;

    /**
     * 岗位
     * <p>
     * PS_AUTH_USER.POSITION
     */
    //@ApiModelProperty(value = "岗位")
    private String position;

    /**
     * 客户编码
     * <p>
     * PS_AUTH_USER.CUST_CODE
     */
    @ApiModelProperty(value = "客户编码")
    private String custCode;

    /**
     * 部门编码
     * <p>
     * PS_AUTH_USER.DEP_CODE
     */
    @ApiModelProperty(value = "部门编码")
    private String depCode;
}