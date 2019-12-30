package com.king.king.api.mapper;

import com.king.king.api.enty.PsAuthPerm;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface PsAuthPermMapper {
    @Delete({
            "delete from PS_AUTH_PERM",
            "where CODE = #{code,jdbcType=VARCHAR}"
    })
    int deleteById(String code);

    @Insert({
            "insert into PS_AUTH_PERM (CODE, PCODE, ",
            "LVL, SEQ, LAYER, ",
            "FIELD, NAME, TYPE, ",
            "APIS, MENU_URL, MENU_ICO)",
            "values (#{code,jdbcType=VARCHAR}, #{pcode,jdbcType=VARCHAR}, ",
            "#{lvl,jdbcType=TINYINT}, #{seq,jdbcType=DECIMAL}, #{layer,jdbcType=VARCHAR}, ",
            "#{field,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
            "#{apis,jdbcType=VARCHAR}, #{menuUrl,jdbcType=VARCHAR}, #{menuIco,jdbcType=VARCHAR})"
    })
    int insertEntity(PsAuthPerm record);

    @Select({
            "select",
            "CODE, PCODE, LVL, SEQ, LAYER, FIELD, NAME, TYPE, APIS, MENU_URL, MENU_ICO",
            "from PS_AUTH_PERM",
            "where CODE = #{code,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "CODE", property = "code", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "PCODE", property = "pcode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "LVL", property = "lvl", jdbcType = JdbcType.TINYINT),
            @Result(column = "SEQ", property = "seq", jdbcType = JdbcType.DECIMAL),
            @Result(column = "LAYER", property = "layer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FIELD", property = "field", jdbcType = JdbcType.VARCHAR),
            @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TYPE", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "APIS", property = "apis", jdbcType = JdbcType.VARCHAR),
            @Result(column = "MENU_URL", property = "menuUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "MENU_ICO", property = "menuIco", jdbcType = JdbcType.VARCHAR)
    })
    PsAuthPerm findById(String code);

    @Select({
            "select",
            "CODE, PCODE, LVL, SEQ, LAYER, FIELD, NAME, TYPE, APIS, MENU_URL, MENU_ICO",
            "from PS_AUTH_PERM",
            "order by age desc,username asc"
    })
    @Results({
            @Result(column = "CODE", property = "code", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "PCODE", property = "pcode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "LVL", property = "lvl", jdbcType = JdbcType.TINYINT),
            @Result(column = "SEQ", property = "seq", jdbcType = JdbcType.DECIMAL),
            @Result(column = "LAYER", property = "layer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FIELD", property = "field", jdbcType = JdbcType.VARCHAR),
            @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TYPE", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "APIS", property = "apis", jdbcType = JdbcType.VARCHAR),
            @Result(column = "MENU_URL", property = "menuUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "MENU_ICO", property = "menuIco", jdbcType = JdbcType.VARCHAR)
    })
    List<PsAuthPerm> findList();

    @Update({
            "update PS_AUTH_PERM",
            "set PCODE = #{pcode,jdbcType=VARCHAR},",
            "LVL = #{lvl,jdbcType=TINYINT},",
            "SEQ = #{seq,jdbcType=DECIMAL},",
            "LAYER = #{layer,jdbcType=VARCHAR},",
            "FIELD = #{field,jdbcType=VARCHAR},",
            "NAME = #{name,jdbcType=VARCHAR},",
            "TYPE = #{type,jdbcType=VARCHAR},",
            "APIS = #{apis,jdbcType=VARCHAR},",
            "MENU_URL = #{menuUrl,jdbcType=VARCHAR},",
            "MENU_ICO = #{menuIco,jdbcType=VARCHAR}",
            "where CODE = #{code,jdbcType=VARCHAR}"
    })
    int updateEntity(PsAuthPerm record);

    @UpdateProvider(type = PsAuthPermSqlBuilder.class, method = PsAuthPermSqlBuilder.BATCH_DELETE_SQL)
    Long batchDelete(List<Long> ids);
}