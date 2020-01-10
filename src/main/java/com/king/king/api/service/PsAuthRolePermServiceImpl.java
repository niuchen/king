package com.king.king.api.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.po.PsAuthRolePermPagePo;
import com.king.king.api.controller.vo.PsAuthRolePermVo;
import com.king.king.api.mapper.PsAuthRolePermMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SendToUser;
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
public class PsAuthRolePermServiceImpl implements PsAuthRolePermService {

    @Autowired
    private PsAuthRolePermMapper psAuthRolePermMapper;

    @Override
    public PageInfo<PsAuthRolePermVo> psAuthUserRolePage(PsAuthRolePermPagePo psAuthRolePermPagePo) {
        PageHelper.startPage(psAuthRolePermPagePo);
        return new PageInfo<PsAuthRolePermVo>(psAuthRolePermMapper.findList());
    }

}
