package com.king.king.api.mapper;

import com.king.king.api.enty.PsAuthUserRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface PsAuthUserRoleMapper {
    @Delete({
            "delete from PS_AUTH_USER_ROLE",
            "where USER_ID = #{userId,jdbcType=DECIMAL}",
            "and ROLE_ID = #{roleId,jdbcType=DECIMAL}"
    })
    int deleteById(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Insert({
            "insert into PS_AUTH_USER_ROLE (USER_ID, ROLE_ID)",
            "values (#{userId,jdbcType=DECIMAL}, #{roleId,jdbcType=DECIMAL})"
    })
    int insertEntity(PsAuthUserRole record);

    @Select({
            "select",
            "USER_ID, ROLE_ID",
            "from PS_AUTH_USER_ROLE",
            "order by age desc,username asc"
    })
    @Results({
            @Result(column = "USER_ID", property = "userId", jdbcType = JdbcType.DECIMAL, id = true),
            @Result(column = "ROLE_ID", property = "roleId", jdbcType = JdbcType.DECIMAL, id = true)
    })
    List<PsAuthUserRole> findList();

    @UpdateProvider(type = PsAuthUserRoleSqlBuilder.class, method = PsAuthUserRoleSqlBuilder.BATCH_DELETE_SQL)
    Long batchDelete(List<Long> ids);
}