package com.king.king.api.service;

import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.po.PsAuthRolePagePo;
import com.king.king.api.controller.po.PsAuthRolePo;
import com.king.king.api.controller.vo.PsAuthRoleVo;

/**
 * 权限角色(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author chen
 * @since 2019/12/25
 */
public interface PsAuthRoleService {

    /**
     * 分页查询权限角色
     */
    PageInfo<PsAuthRoleVo> psAuthRolePage(PsAuthRolePagePo psAuthRolePagePo);

    PsAuthRoleVo selOneRole(Integer id);

    void saveRoles(PsAuthRolePo psAuthUserPo);

    void del(Integer id);
}
