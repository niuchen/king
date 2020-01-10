package com.king.king.api.controller;

import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.po.PsAuthRolePagePo;
import com.king.king.api.controller.po.PsAuthRolePo;
import com.king.king.api.controller.vo.PsAuthRoleVo;
import com.king.king.api.controller.domain.EdpAuthLayer;
import com.king.king.api.service.PsAuthRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 权限角色(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author chen
 * @since 2019/12/25
 */
@Api(tags = "权限角色(实施阶段配置、运营阶段亦可维护、外部审计)")
@Slf4j
@RestController
@RequestMapping("PsAuthRoleApi")
public class PsAuthRoleApi {

    @Autowired
    public PsAuthRoleService psAuthRoleService;

    @GetMapping("/roles")
    @ApiOperation("权限角色分页查询搜索")
    public PageInfo<PsAuthRoleVo> psAuthRolePage(PsAuthRolePagePo psAuthRolePagePo) {
        return psAuthRoleService.psAuthRolePage(psAuthRolePagePo);
    }

    @GetMapping("/roles/{id}")
    @ApiOperation("根据id查询权限角色")
    public PsAuthRoleVo selRoleById(@PathVariable("id") Integer id) {
        return psAuthRoleService.selOneRole(id);
    }

    @PostMapping("/roles")
    @ApiOperation("保存权限角色")
    public void saveRoles(@RequestBody PsAuthRolePo psAuthRolePo) {
        psAuthRolePo.setLayer(EdpAuthLayer.E.name());
        psAuthRoleService.saveRoles(psAuthRolePo);
    }

    @PutMapping("/del/{id}")
    @ApiOperation("根据id删除权限角色")
    public void del(@PathVariable("id") Integer id) {
        psAuthRoleService.del(id);
    }

}
