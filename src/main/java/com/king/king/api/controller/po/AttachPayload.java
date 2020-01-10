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
@ApiModel("更新附件表信息、建立模块与附件绑定关系入参")
public class AttachPayload {

    @ApiModelProperty("附件id")
    private Long id;

    @ApiModelProperty("所属类别id")
    private String entryId;

    @ApiModelProperty("所属类别")
    private String entryCls;

}
