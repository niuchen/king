package com.king.king.api.service;

import com.king.king.api.mapper.PsAuthRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限角色(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author chen
 * @since 2019/12/25
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PsAuthRoleServiceImpl implements PsAuthRoleService {

   // private final PsAuthRoleMapper psAuthRoleMapper;

}
