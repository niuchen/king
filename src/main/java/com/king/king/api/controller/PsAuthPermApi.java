package com.king.king.api.controller;

import com.king.king.api.service.PsAuthPermService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限树(租户无关、实施阶段配置、运营阶段不可维护
 *
 * @author chen
 * @since 2019/12/25
 */
@Api(tags = "权限树(租户无关、实施阶段配置、运营阶段不可维护")
@Slf4j
@RequestMapping("PsAuthPermApi")
@RestController
public class PsAuthPermApi {

    @Autowired
    public PsAuthPermService psAuthPermService;

//    @Autowired
//    public EdpPrincipalService principalService;

//    @GetMapping("/query")
//    @ApiOperation("权限树分页查询搜索")
//    public PageInfo<PsAuthPermVo> psAuthPermPage(PsAuthPermPagePo psAuthPermPagePo) {
//        return psAuthPermService.psAuthPrermPage(psAuthPermPagePo);
//    }
//
//    @GetMapping("/selectPerm/{code}")
//    @ApiOperation("根据编码 查询权限")
//    public List<PsAuthPermVo> selectByPCode(@PathVariable("code") String code) {
//        return psAuthPermService.selectByPCode(code);
//    }
//
//    @GetMapping("/selectPermByLvl/{lvl}")
//    @ApiOperation("根据权限层级 查询权限")
//    public List<PsAuthPermVo> selectPermByLvl(@PathVariable("lvl") Byte lvl) {
//        return psAuthPermService.selectPermByLvl(lvl);
//    }

//    @GetMapping("/perms")
//    public List<AuthPerm> perms() {
//        return AuthPerm.toTrees(psAuthPermService.permsOf(principalService.user().getAuthLayer()));
//    }
}

