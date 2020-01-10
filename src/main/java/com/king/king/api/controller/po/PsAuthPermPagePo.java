package com.king.king.api.controller.po;

import com.king.king.common.PageBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "权限树分页查询分页查询入参")
@EqualsAndHashCode(callSuper=false)
public class PsAuthPermPagePo extends PageBase {

    @ApiModelProperty(value = "父级权限编码")
    private String pcode;

}
