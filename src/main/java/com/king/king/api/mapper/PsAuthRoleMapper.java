package com.king.king.api.mapper;

import com.king.king.api.enty.PsAuthRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface PsAuthRoleMapper {
    @Delete({
            "delete from PS_AUTH_ROLE",
            "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteById(Integer id);

    @Insert({
            "insert into PS_AUTH_ROLE (ID, LAYER, ",
            "FIELD, NAME, DELETE_FLAG)",
            "values (#{id,jdbcType=INTEGER}, #{layer,jdbcType=VARCHAR}, ",
            "#{field,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{deleteFlag,jdbcType=BIT})"
    })
    @SelectKey(statement = "LAST_INSERT_ID()", keyProperty = "id", before = true, resultType = Integer.class)
    int insertEntity(PsAuthRole record);

    @Select({
            "select",
            "ID, LAYER, FIELD, NAME, DELETE_FLAG",
            "from PS_AUTH_ROLE",
            "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "ID", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "LAYER", property = "layer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FIELD", property = "field", jdbcType = JdbcType.VARCHAR),
            @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DELETE_FLAG", property = "deleteFlag", jdbcType = JdbcType.BIT)
    })
    PsAuthRole findById(Integer id);

    @Select({
            "select",
            "ID, LAYER, FIELD, NAME, DELETE_FLAG",
            "from PS_AUTH_ROLE",
            "order by age desc,username asc"
    })
    @Results({
            @Result(column = "ID", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "LAYER", property = "layer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FIELD", property = "field", jdbcType = JdbcType.VARCHAR),
            @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DELETE_FLAG", property = "deleteFlag", jdbcType = JdbcType.BIT)
    })
    List<PsAuthRole> findList();

    @Update({
            "update PS_AUTH_ROLE",
            "set LAYER = #{layer,jdbcType=VARCHAR},",
            "FIELD = #{field,jdbcType=VARCHAR},",
            "NAME = #{name,jdbcType=VARCHAR},",
            "DELETE_FLAG = #{deleteFlag,jdbcType=BIT}",
            "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateEntity(PsAuthRole record);

    @UpdateProvider(type = PsAuthRoleSqlBuilder.class, method = PsAuthRoleSqlBuilder.BATCH_DELETE_SQL)
    Long batchDelete(List<Long> ids);
}