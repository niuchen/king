package com.king.king.api.controller;

import com.king.king.api.controller.po.PsAuthRolePo;
import com.king.king.api.service.PsAuthRoleService;
import com.king.king.common.enums.B2BOpResult;
import com.king.king.util.EdpOpException;
import com.king.king.util.ImportExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * excel 导入API
 *
 * @author shihao.ma
 * @since 2019/12/31
 */
@Slf4j
@RestController
@RequestMapping("importExcel")
@Api(tags = "表格导入api")
public class ExcelImportApi {

    @Autowired
    private PsAuthRoleService psAuthRoleService;

    @ApiOperation("导入角色")
    @PostMapping("/importRole")
    public boolean importRole(@RequestPart("file") MultipartFile file) {

//        EdpUser user = principalService.user();//获取当前用户的权限领域

        try {
            ImportExcelUtil importExcelUtil = new ImportExcelUtil(file, PsAuthRolePo.class);
            List<PsAuthRolePo> psAuthRolePos =
                    (List<PsAuthRolePo>) importExcelUtil.excute();
            for (PsAuthRolePo psAuthRolePo : psAuthRolePos) {
                //校验Excel中的数据
                if (StringUtils.isEmpty(psAuthRolePo.getName())) {
                    throw new EdpOpException(B2BOpResult.NG_MESSAGE, "名称不能为空");
                }
                // 校验通过，设置入参，然后执行客户商品的新增方法
                PsAuthRolePo psAuthRole = new PsAuthRolePo();
                psAuthRoleService.saveRoles(psAuthRole);

            }
        } catch (IllegalArgumentException e) {
            log.error("[BOH-IMPORT] faultInfo soExcleImport save error:{}", e);
            throw new EdpOpException(B2BOpResult.NG_MESSAGE, e.getMessage());
        }
        boolean success = true;
        return success;

    }

}
