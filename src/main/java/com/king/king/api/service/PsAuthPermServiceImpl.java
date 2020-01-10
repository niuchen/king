package com.king.king.api.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.domain.AuthPerm;
import com.king.king.api.controller.domain.EdpAuthLayer;
import com.king.king.api.controller.po.PsAuthPermPagePo;
import com.king.king.api.controller.vo.PsAuthPermVo;
import com.king.king.api.mapper.PsAuthPermMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限树(租户无关、实施阶段配置、运营阶段不可维护
 *
 * @author chen
 * @since 2019/12/25
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PsAuthPermServiceImpl implements PsAuthPermService {

    @Autowired
    private PsAuthPermMapper psAuthPermMapper;

    /**
     * 权限树分页查询
     */
    @Override
    public PageInfo<PsAuthPermVo> psAuthPrermPage(PsAuthPermPagePo psAuthPermPagePo) {
        PageHelper.startPage(psAuthPermPagePo);
        return new PageInfo<PsAuthPermVo>(psAuthPermMapper.PermPage(psAuthPermPagePo));
    }

    /**
     * 根据PCode进行查询
     */
    @Override
    public List<PsAuthPermVo> selectByPCode(String code) {
        PsAuthPermVo psAuthPerm = new PsAuthPermVo();
        psAuthPerm.setPcode(code);
        return psAuthPermMapper.selectPerm(psAuthPerm);
    }

    /**
     * 根据权限层级 lvl进行查询
     * @param lvl
     */
    @Override
    public List<PsAuthPermVo> selectPermByLvl(Byte lvl) {
        PsAuthPermVo psAuthPerm = new PsAuthPermVo();
        psAuthPerm.setLvl(lvl);
        return psAuthPermMapper.selectPerm(psAuthPerm);
    }

    @Override
    public List<AuthPerm> permsOf(EdpAuthLayer layer) {
        return psAuthPermMapper.permsOf(layer);
    }

}
