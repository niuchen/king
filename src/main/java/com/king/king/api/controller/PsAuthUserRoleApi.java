package com.king.king.api.controller;

import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.po.PsAuthUserRolePagePo;
import com.king.king.api.controller.vo.PsAuthUserRoleVo;
import com.king.king.api.service.PsAuthUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色赋权(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author chen
 * @since 2019/12/25
 */
@Api(tags = "角色赋权(实施阶段配置、运营阶段亦可维护、外部审计)")
@Slf4j
@RestController
@RequestMapping("PsAuthUserRoleApi")
public class PsAuthUserRoleApi {

    @Autowired
    public PsAuthUserRoleService psAuthUserRoleService;

    @GetMapping("/query")
    @ApiOperation("用户-角色 对应关系 分页查询搜索")
    public PageInfo<PsAuthUserRoleVo> psAuthUserRolePage(PsAuthUserRolePagePo psAuthUserRolePagePo) {
        return psAuthUserRoleService.psAuthUserRolePage(psAuthUserRolePagePo);
    }


}
