package ${apiTargetPackage};

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.el.mbg.service.${entityName}Service;

/**
* ${tableRemark}
*
* @author ${author}
* @since ${date?string('yyyy/MM/dd')}
*/
@Api(value = "", description = "${tableRemark}")
@Profile("${profile}")
@Slf4j
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class ${entityName}Api {

public final ${entityName}Service ${entityName?uncap_first}Service;

}
