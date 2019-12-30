package ${sqlTargetPackage};

import org.apache.ibatis.jdbc.SQL;
import com.king.king.util.SqlUtil;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
* ${tableRemark}
*
* @author ${author}
* @since ${date?string('yyyy/MM/dd')}
*/
public class ${entityName}SqlBuilder extends SQL {

private void buildSelectSql() {

}

private void buildFromSql() {

}

private void buildFilterSql() {

}

static final String BATCH_DELETE_SQL = "batchDelete";

public String batchDelete(@Param("ids") List
<Long> ids) {
    UPDATE("${tableName}");
    SET("DELETE_FLAG = 1");
    WHERE("ID IN " + SqlUtil.toSqlNumberSet(ids));
    return toString();
    }

    }






