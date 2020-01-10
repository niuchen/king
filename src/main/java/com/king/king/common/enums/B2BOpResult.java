package com.king.king.common.enums;

import com.king.king.util.OpResult;

/**
 * 操作返回码
 *
 * @author niuchen
 * @since 2019/7/18
 */
public enum B2BOpResult implements OpResult {

    /**
     *  后端错误码定义
     */
    OK("操作成功"),
    NG("操作失败"),
    NG_FILE("文件格式不对"),
    NG_MESSAGE("表示要在body里自定义消息"),//请在ErrorMessageEnum类中配置消息枚举
    BOH_NG_PAYLOAD_TO_ENTITY("Payload转换失败"),
    NG_SYS_EXCEPTION("读取用户组织信息异常"),
    NG_NOT_PARAMETER("没有参数"),
    NG_NULL_DATE("没有找到数据"),
    NG_STATE("状态错误"),
    BOH_NG_UOM("单位错误"),

    SO_NG_NOT_PL("没有产品线"),
    SO_NG_NOT_STORETYPE("没有存储类型"),
    NG_DATE("参数错误")
    ;




    private String desc;

    B2BOpResult(String desc) {
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getDesc() {
        return desc;
    }



    String HTTP_HEADER_ATTR = "el-result-code";
    String OK_CODE = "OK";






}
