package com.king.king.api.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.po.PsAuthUserRolePagePo;
import com.king.king.api.controller.vo.PsAuthUserRoleVo;
import com.king.king.api.mapper.PsAuthUserRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色赋权(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author chen
 * @since 2019/12/25
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PsAuthUserRoleServiceImpl implements PsAuthUserRoleService {

    @Autowired
    private PsAuthUserRoleMapper psAuthUserRoleMapper;


    @Override
    public PageInfo<PsAuthUserRoleVo> psAuthUserRolePage(PsAuthUserRolePagePo psAuthUserRolePagePo) {
        PageHelper.startPage(psAuthUserRolePagePo);
        return new PageInfo<PsAuthUserRoleVo>(psAuthUserRoleMapper.findList());
    }
}
