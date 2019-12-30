package ${serviceTargetPackage};

import com.king.king.api.mapper.${entityName}Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* ${tableRemark}
*
* @author ${author}
* @since ${date?string('yyyy/MM/dd')}
*/
@Profile("${profile}")
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ${entityName}ServiceImpl implements ${entityName}Service {

private final ${entityName}Mapper ${entityName?uncap_first}Mapper;

}
