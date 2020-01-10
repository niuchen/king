package com.king.king.api.mapper;


import com.king.king.api.controller.po.PsAuthRolePagePo;
import com.king.king.api.controller.po.PsAuthRolePo;
import com.king.king.util.SqlUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 权限角色(实施阶段配置、运营阶段亦可维护、外部审计)
 *
 * @author chen
 * @since 2019/12/25
 */
public class PsAuthRoleSqlBuilder extends SQL {

    static final String BATCH_DELETE_SQL = "batchDelete";

    public String batchDelete(@Param("ids") List<Long> ids) {
        UPDATE("PS_AUTH_ROLE");
        SET("DELETE_FLAG = 1");
        WHERE("ID IN " + SqlUtil.toSqlNumberSet(ids));
        return toString();
    }

    static final String AUTH_ROLE_QUERY = "authRoleQuery";

    public String authRoleQuery(PsAuthRolePagePo psAuthRolePagePo) {
        SELECT("PAR.ID id, " +
                "PAR.LAYER layer, " +
                "PAR.FIELD field, " +
                "PAR.NAME name, " +
                "PAR.DELETE_FLAG deleteFlag");
        FROM("PS_AUTH_ROLE PAR");
        WHERE("PAR.DELETE_FLAG = 0 OR PAR.DELETE_FLAG IS NULL");
        ORDER_BY("PAR.ID DESC");
        return toString();
    }

    static final String NAME_IF_EXISTS_SQL = "nameIfExistsSql";

    public String nameIfExistsSql(PsAuthRolePo entity) {
        SELECT("case when count(0) > 0 then 1 else 0 end");
        FROM("PS_AUTH_ROLE");
        WHERE("LAYER = #{layer}");
        WHERE("FIELD = #{field}");
        WHERE("NAME = #{name}");
        if (entity.getId() != null) {
            WHERE("ID <> #{id}");
        }
        return toString();
    }

}






