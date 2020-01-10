package com.king.king.api.controller.po;

import com.king.king.common.PageBase;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "用户分页查询入参")
@EqualsAndHashCode(callSuper=false)
public class PsAuthUserPagePo extends PageBase {


}
