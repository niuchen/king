package com.king.king.api.controller.domain;

import java.io.Serializable;

/**
 * @author shihao.ma
 * @since 2019/12/31
 */
public interface EdpUser extends Serializable {

    /**
     * ID
     */
    Long getId();

    /*------------------+
     |   以下为权限属性   |
     +=================*/

    /**
     * 租户ID
     */
    Long getTenantId();

    /**
     * 权限层级
     */
    EdpAuthLayer getAuthLayer();

    /**
     * 权限领域
     */
    EdpAuthField getAuthField();

    /*------------------+
     |   以下为业务属性   |
     +=================*/

    /**
     * 企业类型
     */
    EdpEntType getEntType();

    /**
     * 企业ID
     */
    Long getEntId();

    /**
     * 企业号
     */
    String getEntCode();

    /**
     * 企业地址号
     */
    Long getEntAddr();

    /**
     * 员工ID
     */
    Long getEmpId();

    /**
     * 员工号(即所属企业内用户名)
     */
    String getEmpCode();

    /**
     * 员工地址号
     */
    Long getEmpAddr();

    /**
     * 用户名称(显示用)
     */
    String getName();

    /**
     * 手机
     */
    String getPhone();

    /**
     * 邮箱
     */
    String getEmail();


    /**客户编码 部门编码 20190823 haoyang.chang**/
    /**
     * 登录名
     */
    String getLoginName();
    /**
     * 客户编码
     */
    String getCustCode();

    /**
     * 公司名称
     */
    String getOuName();

    /**
     * 客户名称
     */
    String getCustName();

    /**
     * 部门编码
     */
    String getOrgCode();

    /**
     * 岗位
     */
    String getPosition();

}
