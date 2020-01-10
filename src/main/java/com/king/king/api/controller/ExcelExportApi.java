package com.king.king.api.controller;

import com.king.king.api.controller.vo.PsAuthRoleVo;
import com.king.king.api.mapper.PsAuthRoleMapper;
import com.king.king.util.ExcelExportUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * excel导出接口.
 *
 * @author shihao.ma
 * @since 2019-12-31
 */
@Slf4j
@RestController
@RequestMapping("exportExcel")
@Api(tags = "excel表格导出api")
public class ExcelExportApi {

    @Autowired
    private PsAuthRoleMapper psAuthRoleMapper;

    /*
     * excel导出例子
     * */
    @ApiOperation("角色信息导出")
    @GetMapping("/authRole")
    public void authUserExcelExport(@RequestParam(defaultValue = "用户信息") String filename,
                                    @RequestParam(defaultValue = "8") Integer timeZone,
                                    HttpServletResponse response,
                                    String... columns) throws UnsupportedEncodingException {
        List<PsAuthRoleVo> list = psAuthRoleMapper.findList();
        ExcelExportUtil.on(list, PsAuthRoleVo.class, columns).buildExcel(response, filename, timeZone);
    }

}
