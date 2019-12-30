package com.king.king.api.mapper;

import com.king.king.api.controller.vo.PsAuthUserVo;
import com.king.king.api.enty.PsAuthUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface PsAuthUserMapper {
    @Delete({
            "delete from PS_AUTH_USER",
            "where ID = #{id,jdbcType=INTEGER}"
    })
    int deleteById(Integer id);

    @Insert({
            "insert into PS_AUTH_USER (ID, LAYER, ",
            "FIELD, ENT_ID, ENT_CODE, ",
            "ENT_ADDR, EMP_ID, ",
            "EMP_CODE, EMP_ADDR, ",
            "PASSWORD, SALT, ",
            "NAME, EMAIL, PHONE, ",
            "DELETE_FLAG, ENT_TYPE, ",
            "LOGIN_NAME, POSITION, ",
            "CUST_CODE, DEP_CODE)",
            "values (#{id,jdbcType=INTEGER}, #{layer,jdbcType=VARCHAR}, ",
            "#{field,jdbcType=VARCHAR}, #{entId,jdbcType=DECIMAL}, #{entCode,jdbcType=VARCHAR}, ",
            "#{entAddr,jdbcType=DECIMAL}, #{empId,jdbcType=DECIMAL}, ",
            "#{empCode,jdbcType=VARCHAR}, #{empAddr,jdbcType=DECIMAL}, ",
            "#{password,jdbcType=VARCHAR}, #{salt,jdbcType=VARCHAR}, ",
            "#{name,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
            "#{deleteFlag,jdbcType=BIT}, #{entType,jdbcType=VARCHAR}, ",
            "#{loginName,jdbcType=VARCHAR}, #{position,jdbcType=VARCHAR}, ",
            "#{custCode,jdbcType=VARCHAR}, #{depCode,jdbcType=VARCHAR})"
    })
    @SelectKey(statement = "LAST_INSERT_ID()", keyProperty = "id", before = true, resultType = Integer.class)
    int insertEntity(PsAuthUser record);

    @Select({
            "select",
            "ID, LAYER, FIELD, ENT_ID, ENT_CODE, ENT_ADDR, EMP_ID, EMP_CODE, EMP_ADDR, PASSWORD, ",
            "SALT, NAME, EMAIL, PHONE, DELETE_FLAG, ENT_TYPE, LOGIN_NAME, POSITION, CUST_CODE, ",
            "DEP_CODE",
            "from PS_AUTH_USER",
            "where ID = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "ID", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "LAYER", property = "layer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FIELD", property = "field", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ENT_ID", property = "entId", jdbcType = JdbcType.DECIMAL),
            @Result(column = "ENT_CODE", property = "entCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ENT_ADDR", property = "entAddr", jdbcType = JdbcType.DECIMAL),
            @Result(column = "EMP_ID", property = "empId", jdbcType = JdbcType.DECIMAL),
            @Result(column = "EMP_CODE", property = "empCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "EMP_ADDR", property = "empAddr", jdbcType = JdbcType.DECIMAL),
            @Result(column = "PASSWORD", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SALT", property = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "EMAIL", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PHONE", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DELETE_FLAG", property = "deleteFlag", jdbcType = JdbcType.BIT),
            @Result(column = "ENT_TYPE", property = "entType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "LOGIN_NAME", property = "loginName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "POSITION", property = "position", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CUST_CODE", property = "custCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEP_CODE", property = "depCode", jdbcType = JdbcType.VARCHAR)
    })
    PsAuthUser findById(Integer id);

    @Select({
            "select",
            "ID, LAYER, FIELD, ENT_ID, ENT_CODE, ENT_ADDR, EMP_ID, EMP_CODE, EMP_ADDR, PASSWORD, ",
            "SALT, NAME, EMAIL, PHONE, DELETE_FLAG, ENT_TYPE, LOGIN_NAME, POSITION, CUST_CODE, ",
            "DEP_CODE",
            "from PS_AUTH_USER",
            "order by ID desc"
    })
    @Results({
            @Result(column = "ID", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "LAYER", property = "layer", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FIELD", property = "field", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ENT_ID", property = "entId", jdbcType = JdbcType.DECIMAL),
            @Result(column = "ENT_CODE", property = "entCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ENT_ADDR", property = "entAddr", jdbcType = JdbcType.DECIMAL),
            @Result(column = "EMP_ID", property = "empId", jdbcType = JdbcType.DECIMAL),
            @Result(column = "EMP_CODE", property = "empCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "EMP_ADDR", property = "empAddr", jdbcType = JdbcType.DECIMAL),
            @Result(column = "PASSWORD", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "SALT", property = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(column = "NAME", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "EMAIL", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "PHONE", property = "phone", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DELETE_FLAG", property = "deleteFlag", jdbcType = JdbcType.BIT),
            @Result(column = "ENT_TYPE", property = "entType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "LOGIN_NAME", property = "loginName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "POSITION", property = "position", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CUST_CODE", property = "custCode", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEP_CODE", property = "depCode", jdbcType = JdbcType.VARCHAR)
    })
    List<PsAuthUserVo> findList();

    @Update({
            "update PS_AUTH_USER",
            "set LAYER = #{layer,jdbcType=VARCHAR},",
            "FIELD = #{field,jdbcType=VARCHAR},",
            "ENT_ID = #{entId,jdbcType=DECIMAL},",
            "ENT_CODE = #{entCode,jdbcType=VARCHAR},",
            "ENT_ADDR = #{entAddr,jdbcType=DECIMAL},",
            "EMP_ID = #{empId,jdbcType=DECIMAL},",
            "EMP_CODE = #{empCode,jdbcType=VARCHAR},",
            "EMP_ADDR = #{empAddr,jdbcType=DECIMAL},",
            "PASSWORD = #{password,jdbcType=VARCHAR},",
            "SALT = #{salt,jdbcType=VARCHAR},",
            "NAME = #{name,jdbcType=VARCHAR},",
            "EMAIL = #{email,jdbcType=VARCHAR},",
            "PHONE = #{phone,jdbcType=VARCHAR},",
            "DELETE_FLAG = #{deleteFlag,jdbcType=BIT},",
            "ENT_TYPE = #{entType,jdbcType=VARCHAR},",
            "LOGIN_NAME = #{loginName,jdbcType=VARCHAR},",
            "POSITION = #{position,jdbcType=VARCHAR},",
            "CUST_CODE = #{custCode,jdbcType=VARCHAR},",
            "DEP_CODE = #{depCode,jdbcType=VARCHAR}",
            "where ID = #{id,jdbcType=INTEGER}"
    })
    int updateEntity(PsAuthUser record);

    @UpdateProvider(type = PsAuthUserSqlBuilder.class, method = PsAuthUserSqlBuilder.BATCH_DELETE_SQL)
    Long batchDelete(List<Long> ids);
}