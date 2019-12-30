package com.king.king.api.mapper;

import com.king.king.util.SqlUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 权限树(租户无关、实施阶段配置、运营阶段不可维护
 *
 * @author chen
 * @since 2019/12/25
 */
public class PsAuthPermSqlBuilder extends SQL {

    private void buildSelectSql() {

    }

    private void buildFromSql() {

    }

    private void buildFilterSql() {

    }

    static final String BATCH_DELETE_SQL = "batchDelete";

    public String batchDelete(@Param("ids") List<Long> ids) {
        UPDATE("PS_AUTH_PERM");
        SET("DELETE_FLAG = 1");
        WHERE("ID IN " + SqlUtil.toSqlNumberSet(ids));
        return toString();
    }

}






