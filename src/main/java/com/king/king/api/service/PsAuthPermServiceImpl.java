package com.king.king.api.service;

import com.king.king.api.mapper.PsAuthPermMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限树(租户无关、实施阶段配置、运营阶段不可维护
 *
 * @author chen
 * @since 2019/12/25
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class PsAuthPermServiceImpl implements PsAuthPermService {

   // private final PsAuthPermMapper psAuthPermMapper;

}
