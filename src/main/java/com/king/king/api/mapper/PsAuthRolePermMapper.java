package com.king.king.api.mapper;

import com.king.king.api.enty.PsAuthRolePerm;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface PsAuthRolePermMapper {
    @Delete({
            "delete from PS_AUTH_ROLE_PERM",
            "where ROLE_ID = #{roleId,jdbcType=DECIMAL}",
            "and PERM_CODE = #{permCode,jdbcType=VARCHAR}"
    })
    int deleteById(@Param("roleId") Long roleId, @Param("permCode") String permCode);

    @Insert({
            "insert into PS_AUTH_ROLE_PERM (ROLE_ID, PERM_CODE)",
            "values (#{roleId,jdbcType=DECIMAL}, #{permCode,jdbcType=VARCHAR})"
    })
    int insertEntity(PsAuthRolePerm record);

    @Select({
            "select",
            "ROLE_ID, PERM_CODE",
            "from PS_AUTH_ROLE_PERM",
            "order by age desc,username asc"
    })
    @Results({
            @Result(column = "ROLE_ID", property = "roleId", jdbcType = JdbcType.DECIMAL, id = true),
            @Result(column = "PERM_CODE", property = "permCode", jdbcType = JdbcType.VARCHAR, id = true)
    })
    List<PsAuthRolePerm> findList();

    @UpdateProvider(type = PsAuthRolePermSqlBuilder.class, method = PsAuthRolePermSqlBuilder.BATCH_DELETE_SQL)
    Long batchDelete(List<Long> ids);
}