package com.king.king.api.mapper;

import com.king.king.api.controller.po.PsAuthUserPagePo;
import com.king.king.util.SqlUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * 系统用户(实施阶段配置平台管理员、运营阶段除了改密码外不可维护)
 *
 * @author chen
 * @since 2019/12/25
 */
public class PsAuthUserSqlBuilder extends SQL {

    static final String BATCH_DELETE_SQL = "batchDelete";

    public String batchDelete(@Param("ids") List<Long> ids) {
        UPDATE("PS_AUTH_USER");
        SET("DELETE_FLAG = 1");
        WHERE("ID IN " + SqlUtil.toSqlNumberSet(ids));
        return toString();
    }

    static final String AUTH_USER_QUUERY = "authUserQuery";

    public String authUserQuery(PsAuthUserPagePo psAuthUserPagePo) {
        SELECT("PAU.ID id, " +
                "PAU.LAYER layer, " +
                "PAU.FIELD field, " +
                "PAU.ENT_ID entId, " +
                "PAU.ENT_CODE entCode, " +
                "PAU.ENT_ADDR entAddr, " +
                "PAU.EMP_ID empId, " +
                "PAU.EMP_CODE empCode, " +
                "PAU.EMP_ADDR emtAddr, " +
                "PAU.PASSWORD password, " +
                "PAU.SALT salt, " +
                "PAU.NAME name, " +
                "PAU.EMAIL email, " +
                "PAU.PHONE phone, " +
                "PAU.DELETE_FLAG deleteFlag, " +
                "PAU.ENT_TYPE entType, " +
                "PAU.LOGIN_NAME loginName, " +
                "PAU.POSITION position, " +
                "PAU.CUST_CODE custCode, " +
                "PAU.DEP_CODE depCode");
        FROM("PS_AUTH_USER PAU");
        WHERE("PAU.DELETE_FLAG = 0 OR PAU.DELETE_FLAG IS NULL");
        ORDER_BY("PAU.ID DESC");
        return toString();
    }

}






