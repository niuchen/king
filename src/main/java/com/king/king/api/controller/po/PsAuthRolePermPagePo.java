package com.king.king.api.controller.po;

import com.king.king.common.PageBase;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(value = "角色-权限 对应关系 分页查询入参")
@EqualsAndHashCode(callSuper=false)
public class PsAuthRolePermPagePo extends PageBase {


}
