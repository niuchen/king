package ${sqlTargetPackage};

import com.el.core.domain.PagingQuery;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* ${tableRemark}
*
* @author ${author}
* @since ${date?string('yyyy/MM/dd')}
*/
@ApiModel("${tableRemark}")
@Data
@EqualsAndHashCode(callSuper = true)
public class ${entityName}Criteria extends PagingQuery {

}
