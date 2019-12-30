package com.king.king.api.mapper;


import com.king.king.util.SqlUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 角色赋权(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author chen
 * @since 2019/12/25
 */
public class PsAuthRolePermSqlBuilder extends SQL {

    private void buildSelectSql() {

    }

    private void buildFromSql() {

    }

    private void buildFilterSql() {

    }

    static final String BATCH_DELETE_SQL = "batchDelete";

    public String batchDelete(@Param("ids") List<Long> ids) {
        UPDATE("PS_AUTH_ROLE_PERM");
        SET("DELETE_FLAG = 1");
        WHERE("ID IN " + SqlUtil.toSqlNumberSet(ids));
        return toString();
    }

}






