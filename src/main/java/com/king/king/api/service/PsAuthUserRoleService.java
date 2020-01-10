package com.king.king.api.service;

import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.po.PsAuthUserRolePagePo;
import com.king.king.api.controller.vo.PsAuthUserRoleVo;

/**
 * 角色赋权(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author chen
 * @since 2019/12/25
 */
public interface PsAuthUserRoleService {

    PageInfo<PsAuthUserRoleVo> psAuthUserRolePage(PsAuthUserRolePagePo psAuthUserRolePagePo);
}
