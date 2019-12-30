package com.king.king.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限角色(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author chen
 * @since 2019/12/25
 */
//@Api(value = "", description = "权限角色(实施阶段配置、运营阶段亦可维护、外部审计)")
@Slf4j
@RestController
@RequestMapping("PsAuthRoleApi")
@RequiredArgsConstructor
public class PsAuthRoleApi {

  //  public final PsAuthRoleService psAuthRoleService;

}
