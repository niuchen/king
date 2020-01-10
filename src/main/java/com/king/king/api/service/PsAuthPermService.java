package com.king.king.api.service;

import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.domain.AuthPerm;
import com.king.king.api.controller.domain.EdpAuthLayer;
import com.king.king.api.controller.po.PsAuthPermPagePo;
import com.king.king.api.controller.vo.PsAuthPermVo;

import java.util.List;

/**
 * 权限树(租户无关、实施阶段配置、运营阶段不可维护
 *
 * @author chen
 * @since 2019/12/25
 */
public interface PsAuthPermService {

    /**
     * 权限树分页查询
     */
    PageInfo<PsAuthPermVo> psAuthPrermPage(PsAuthPermPagePo psAuthPermPagePo);

    List<PsAuthPermVo> selectByPCode(String code);

    List<PsAuthPermVo> selectPermByLvl(Byte lvl);

    List<AuthPerm> permsOf(EdpAuthLayer layer);
}
