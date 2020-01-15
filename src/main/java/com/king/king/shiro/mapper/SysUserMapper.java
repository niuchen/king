package com.king.king.shiro.mapper;

import com.king.king.api.controller.vo.PsAuthUserVo;
import com.king.king.api.enty.PsAuthUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

public interface SysUserMapper   {
    @Select({
            "select",
            "ID, LAYER, FIELD, ENT_ID, ENT_CODE, ENT_ADDR, EMP_ID, EMP_CODE, EMP_ADDR, PASSWORD, ",
            "SALT, NAME, EMAIL, PHONE, DELETE_FLAG, ENT_TYPE, LOGIN_NAME, POSITION, CUST_CODE, ",
            "DEP_CODE",
            "from PS_AUTH_USER",
            "where LOGIN_NAME = #{loginName} and PASSWORD = #{password} "
    })
    PsAuthUser PsAuthUser(PsAuthUser user);


//        @Select("SELECT sre.res_url FROM sys_user_role sur LEFT JOIN sys_user su ON su.id = sur.user_id \n" +
//            "LEFT JOIN sys_role sr ON sur.role_id=sr.id LEFT JOIN sys_role_resources srr ON sur.role_id = srr.role_id\n" +
//            "LEFT JOIN sys_resources sre ON sre.id = srr.resources_id\n" +
//            "WHERE su.id=#{userId}")
        @Select({"select 'ddd' from PS_AUTH_USER"})
        Set<String> findRoleNameByUserId(@Param("userId") int userId);


}