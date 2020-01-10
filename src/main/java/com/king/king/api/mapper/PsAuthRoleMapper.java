package com.king.king.api.mapper;

import com.king.king.api.controller.po.PsAuthRolePagePo;
import com.king.king.api.controller.po.PsAuthRolePo;
import com.king.king.api.controller.vo.PsAuthRoleVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface PsAuthRoleMapper {
//    @Delete({
//            "delete from PS_AUTH_ROLE",
//            "where ID = #{id,jdbcType=INTEGER}"
//    })
//    int deleteById(Integer id);

    @Update({
            "update PS_AUTH_ROLE set DELETE_FLAG = 1 ",
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
    int insertEntity(PsAuthRolePo record);

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
    PsAuthRoleVo findById(Integer id);

    @Select({
            "select",
            "ID, LAYER, FIELD, NAME, DELETE_FLAG",
            "from PS_AUTH_ROLE",
            "order by ID desc"
    })
    @Results({
            @Result(column = "ID", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "LAYER", property = "layer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FIELD", property = "field", jdbcType = JdbcType.VARCHAR),
            @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DELETE_FLAG", property = "deleteFlag", jdbcType = JdbcType.BIT)
    })
    List<PsAuthRoleVo> findList();

    @Update({
            "update PS_AUTH_ROLE",
            "set LAYER = #{layer,jdbcType=VARCHAR},",
            "FIELD = #{field,jdbcType=VARCHAR},",
            "NAME = #{name,jdbcType=VARCHAR},",
            "DELETE_FLAG = #{deleteFlag,jdbcType=BIT}",
            "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateEntity(PsAuthRolePo record);

    @UpdateProvider(type = PsAuthRoleSqlBuilder.class, method = PsAuthRoleSqlBuilder.BATCH_DELETE_SQL)
    Long batchDelete(List<Long> ids);

    /**
     * 根据条件进行模糊查询
     */
    @SelectProvider(type = PsAuthRoleSqlBuilder.class, method = PsAuthRoleSqlBuilder.AUTH_ROLE_QUERY)
    List<PsAuthRoleVo> authRoleQuery(PsAuthRolePagePo psAuthRolePagePo);

    /**
     * @param entity 角色对象
     * @return 对应角色层级、领域下是否存在指定角色名
     */
    @SelectProvider(type = PsAuthRoleSqlBuilder.class, method = PsAuthRoleSqlBuilder.NAME_IF_EXISTS_SQL)
    boolean nameIfExists(PsAuthRolePo entity);
}