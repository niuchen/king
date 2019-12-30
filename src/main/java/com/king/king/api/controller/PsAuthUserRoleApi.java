package com.king.king.api.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色赋权(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author chen
 * @since 2019/12/25
 */
@Api(value = "", tags = "角色赋权(实施阶段配置、运营阶段亦可维护、外部审计)")
@Slf4j
@RestController
@RequestMapping("PsAuthUserRoleApi")
@RequiredArgsConstructor
public class PsAuthUserRoleApi {

  //  public final PsAuthUserRoleService psAuthUserRoleService;

}
