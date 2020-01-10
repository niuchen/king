package com.king.king.api.controller;

import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.po.PsAuthUserPagePo;
import com.king.king.api.controller.po.PsAuthUserPo;
import com.king.king.api.controller.vo.PsAuthUserVo;
import com.king.king.api.service.PsAuthUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统用户(实施阶段配置平台管理员、运营阶段除了改密码外不可维护)
 *
 * @author chen
 * @since 2019/12/25
 */
@Api(tags = "系统用户(实施阶段配置平台管理员、运营阶段除了改密码外不可维护)")
@Slf4j
@RestController
@RequestMapping("PsAuthUserApi")
public class PsAuthUserApi {

    @Autowired
    public PsAuthUserService psAuthUserService;


    @GetMapping("/query")
    @ApiOperation("系统用户分页查询搜索")
    public PageInfo<PsAuthUserVo> psAuthUserPage(PsAuthUserPagePo psAuthUserPagePo2) {
        PageInfo<PsAuthUserVo> psAuthUserVoPageInfo = psAuthUserService.psAuthUserPage(psAuthUserPagePo2);
        return psAuthUserVoPageInfo;
    }

    @GetMapping("/queryPsAuthUserVo")
    public PsAuthUserVo psAuthUserPage() {
        List<PsAuthUserVo> psAuthUserVos = psAuthUserService.psAuthUserPage();
        return psAuthUserVos.get(0);
    }

    @GetMapping("/query/{id}")
    @ApiOperation("根据id查询系统用户")
    public PsAuthUserVo selectUserById(@PathVariable("id") Integer id) {
        return psAuthUserService.selectUserById(id);
    }

    @PostMapping("/add")
    @ApiOperation("新增系统用户")
    public void add(@RequestBody PsAuthUserPo psAuthUserPo) {
        psAuthUserService.add(psAuthUserPo);
    }

    @PutMapping("/del/{id}")
    @ApiOperation("根据id删除系统用户")
    public void del(@PathVariable("id") Integer id) {
        psAuthUserService.del(id);
    }
}
