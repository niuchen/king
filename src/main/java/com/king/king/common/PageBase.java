package com.king.king.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询入参")
public class PageBase {
    //    Integer offset;
//    Integer limit;
    @ApiModelProperty(value = "页码，从1开始")
    int pageNum=0;
    @ApiModelProperty(value = "多少条")
    int pageSize=10;
}
