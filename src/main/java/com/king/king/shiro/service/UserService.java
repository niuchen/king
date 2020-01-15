package com.king.king.shiro.service;

import com.king.king.api.enty.PsAuthUser;

import java.util.Set;

public interface UserService {
    /***
     * 获取用户
     *
     * @param user
     * @return
     */
    PsAuthUser getUser(PsAuthUser user);

    /**
     * 获取用户权限
     *
     * @param userId userId
     * @return 用户权限
     */
    Set<String> findPermissionsByUserId(int userId);

    Set<String> findRoleNameByUserId(Integer id);
}
