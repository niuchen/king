package com.king.king.api.mapper;

import com.king.king.api.controller.po.PsAuthPermPagePo;
import com.king.king.api.controller.vo.PsAuthPermVo;
import com.king.king.util.SqlUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 权限树(租户无关、实施阶段配置、运营阶段不可维护
 *
 * @author chen
 * @since 2019/12/25
 */
public class PsAuthPermSqlBuilder extends SQL {

    static final String BATCH_DELETE_SQL = "batchDelete";

    public String batchDelete(@Param("ids") List<Long> ids) {
        UPDATE("PS_AUTH_PERM");
        SET("DELETE_FLAG = 1");
        WHERE("ID IN " + SqlUtil.toSqlNumberSet(ids));
        return toString();
    }

    static final String SELECT_PERM_PAGE = "selectPermPage";

    public String selectPermPage(PsAuthPermPagePo psAuthPermPagePo) {
        SELECT("PAP.CODE code, " +
                "PAP.PCODE pcode, " +
                "PAP.LVL lvl, " +
                "PAP.SEQ seq, " +
                "PAP.LAYER layer, " +
                "PAP.FIELD field, " +
                "PAP.NAME name, " +
                "PAP.TYPE type, " +
                "PAP.APIS apis, " +
                "PAP.MENU_URL menuUrl, " +
                "PAP.MENU_ICO menuIco");
        FROM("PS_AUTH_PERM PAP");
        if(!StringUtils.isEmpty(psAuthPermPagePo.getPcode())){
            WHERE("PAP.PCODE LIKE '%" + psAuthPermPagePo.getPcode() + "%'");
        }
        ORDER_BY("PAP.CODE DESC");
        return toString();
    }

    static final String SELECT_PERM = "selectPerm";

    public String selectPerm(PsAuthPermVo psAuthPerm) {
        SELECT("PAP.CODE code, " +
                "PAP.PCODE pcode, " +
                "PAP.LVL lvl, " +
                "PAP.SEQ seq, " +
                "PAP.LAYER layer, " +
                "PAP.FIELD field, " +
                "PAP.NAME name, " +
                "PAP.TYPE type, " +
                "PAP.APIS apis, " +
                "PAP.MENU_URL menuUrl, " +
                "PAP.MENU_ICO menuIco");
        FROM("PS_AUTH_PERM PAP");
        if(!StringUtils.isEmpty(psAuthPerm.getPcode())){
            WHERE("PAP.PCODE = " + SqlUtil.toSqlString(psAuthPerm.getPcode()));
        }
        if(!StringUtils.isEmpty(psAuthPerm.getLvl())){
            WHERE("PAP.LVL = " + psAuthPerm.getLvl());
        }
        ORDER_BY("PAP.SEQ DESC");
        return toString();
    }

}






