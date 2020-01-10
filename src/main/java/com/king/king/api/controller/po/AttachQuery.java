package com.king.king.api.controller.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * description: .
 *
 * @author 赵乔功
 * @date 2019-08-06
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode
@ApiModel("附件信息查询的入参")
public class AttachQuery {

    @ApiModelProperty("所属类别id")
    private String entryId;

    @ApiModelProperty("所属类别")
    private String entryCls;

    @ApiModelProperty("查询条数、查询0-limit条记录,不传参默认查全部")
    private Integer limit;

    @ApiModelProperty("企业code")
    private String entCode;

}
