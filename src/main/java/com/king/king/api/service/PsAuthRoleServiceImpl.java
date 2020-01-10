package com.king.king.api.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.po.PsAuthRolePagePo;
import com.king.king.api.controller.po.PsAuthRolePo;
import com.king.king.api.controller.vo.PsAuthRoleVo;
import com.king.king.api.mapper.PsAuthPermMapper;
import com.king.king.api.mapper.PsAuthRoleMapper;
import com.king.king.api.mapper.PsAuthRolePermMapper;
import com.king.king.util.EdpOpException;
import com.king.king.util.OpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.security.Permission;

/**
 * 权限角色(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author chen
 * @since 2019/12/25
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PsAuthRoleServiceImpl implements PsAuthRoleService {

    @Autowired
    private PsAuthRoleMapper psAuthRoleMapper;

    @Autowired
    private PsAuthRolePermMapper psAuthRolePermMapper;

    /**
     * 分页查询权限角色
     */
    @Override
    public PageInfo<PsAuthRoleVo> psAuthRolePage(PsAuthRolePagePo psAuthRolePagePo) {
        PageHelper.startPage(psAuthRolePagePo);
        return new PageInfo<PsAuthRoleVo>(psAuthRoleMapper.authRoleQuery(psAuthRolePagePo));
    }

    /**
     * 根据ID查询权限角色
     */
    @Override
    public PsAuthRoleVo selOneRole(Integer id) {
        return psAuthRoleMapper.findById(id);
    }

    /**
     * 保存权限角色
     *
     * @param psAuthUserPo 入参
     */
    @Override
    public void saveRoles(PsAuthRolePo psAuthUserPo) {
        if (psAuthRoleMapper.nameIfExists(psAuthUserPo)) {
            throw new EdpOpException(OpResult.NG, "角色已被定义");
        }
        if (StringUtils.isEmpty(psAuthUserPo.getId())) {
            // 新增角色
            psAuthRoleMapper.insertEntity(psAuthUserPo);
        } else {
            // 修改角色
            psAuthRoleMapper.updateEntity(psAuthUserPo);
            // 删除原先角色对应的权限
            psAuthRolePermMapper.deleteByRoleId(psAuthUserPo.getId());
        }
        // 插入角色的权限
        psAuthUserPo.getPerms().forEach(perm -> psAuthRolePermMapper.insertRolePerm(psAuthUserPo.getId(), perm));

    }

    /**
     * 删除权限角色
     *
     * @param id 权限角色ID
     */
    @Override
    public void del(Integer id) {
        psAuthRoleMapper.deleteById(id);
        psAuthRolePermMapper.deleteByRoleId(id);
    }

}
