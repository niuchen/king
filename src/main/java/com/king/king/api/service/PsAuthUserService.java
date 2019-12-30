package com.king.king.api.service;

import com.github.pagehelper.PageInfo;
import com.king.king.api.controller.po.PsAuthUserPagePo;
import com.king.king.api.controller.vo.PsAuthUserVo;

import java.util.List;

/**
 * 系统用户(实施阶段配置平台管理员、运营阶段除了改密码外不可维护)
 *
 * @author chen
 * @since 2019/12/25
 */
public interface PsAuthUserService {

    /***分页查询***/
   // PageInfo<PsAuthUserVo> psAuthUserPage(PsAuthUserPagePo psAuthUserPagePo);

     List<PsAuthUserVo> psAuthUserPage();
    PageInfo<PsAuthUserVo> psAuthUserPage(PsAuthUserPagePo psAuthUserPagePo);

}
