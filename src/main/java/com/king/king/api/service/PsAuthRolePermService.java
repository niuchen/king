package com.king.king.api.service;

import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.po.PsAuthRolePermPagePo;
import com.king.king.api.controller.vo.PsAuthRolePermVo;

/**
 * 角色赋权(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author chen
 * @since 2019/12/25
 */
public interface PsAuthRolePermService {

    PageInfo<PsAuthRolePermVo> psAuthUserRolePage(PsAuthRolePermPagePo psAuthRolePermPagePo);
}
