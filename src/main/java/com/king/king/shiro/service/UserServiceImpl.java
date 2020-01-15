package com.king.king.shiro.service;

import com.king.king.api.enty.PsAuthUser;
import com.king.king.shiro.mapper.SysUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper userMapper;



    @Override
    public PsAuthUser getUser(PsAuthUser user) {
        PsAuthUser info = userMapper.PsAuthUser(user);
        return info;
    }

    @Override
    public Set<String> findPermissionsByUserId(int userId) {
        Set<String> permissions = userMapper.findRoleNameByUserId(userId);
//        Set<String> result = new HashSet<>();
//        for (String permission : permissions) {
//            if (StringUtils.isBlank(permission)) {
//                continue;
//            }
//            permission = StringUtils.trim(permission);
//            result.addAll(Arrays.asList(permission.split("\\s*;\\s*")));
//        }
        return permissions;
    }

    @Override
    public Set<String> findRoleNameByUserId(Integer id) {
        Set<String> roleName = userMapper.findRoleNameByUserId(id);
        return roleName;
    }
}
