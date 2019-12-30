package com.king.king.api.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.po.PsAuthUserPagePo;
import com.king.king.api.controller.vo.PsAuthUserVo;
import com.king.king.api.mapper.PsAuthUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统用户(实施阶段配置平台管理员、运营阶段除了改密码外不可维护)
 *
 * @author chen
 * @since 2019/12/25
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PsAuthUserServiceImpl implements PsAuthUserService {

    @Autowired
    private    PsAuthUserMapper psAuthUserMapper;

    @Override
    public List<PsAuthUserVo> psAuthUserPage() {
        return psAuthUserMapper.findList();
    }
    @Override
    public PageInfo<PsAuthUserVo> psAuthUserPage(PsAuthUserPagePo psAuthUserPagePo) {
        PageHelper.startPage(psAuthUserPagePo);
        List<PsAuthUserVo> psAuthUserPagePolist = psAuthUserMapper.findList();
        PageInfo<PsAuthUserVo> pageInfo = new PageInfo<PsAuthUserVo>(psAuthUserPagePolist);
        return pageInfo;
    }
}
