package com.king.king.api.enty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Administrator
 * @since 2019-07-29
 * PB_ATTACH
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class PbAttach {
    /**
     * 主键
     *
     * PB_ATTACH.ID
     */
    @ApiModelProperty(value="主键")
    private Long id;

    /**
     * 实体类别
     *
     * PB_ATTACH.ENTRY_CLS
     */
    @ApiModelProperty(value="实体类别")
    private String entryCls;

    /**
     * 实体ID
     *
     * PB_ATTACH.ENTRY_ID
     */
    @ApiModelProperty(value="实体ID或Code")
    private String entryId;

    /**
     * 文件名
     *
     * PB_ATTACH.ATTACH_NAME
     */
    @ApiModelProperty(value="文件名")
    private String attachName;

    /**
     * 类型1
     *
     * PB_ATTACH.ATTACH_TYPE1
     */
//    @ApiModelProperty(value="类型1")
//    private String attachType1;

    /**
     * 类型2
     *
     * PB_ATTACH.ATTACH_TYPE2
     */
    @ApiModelProperty(value="类型2")
    private String attachType2;

    /**
     * 类型3
     *
     * PB_ATTACH.ATTACH_TYPE3
     */
    @ApiModelProperty(value="类型3")
    private String attachType3;

    /**
     * 文档数据类型
     *
     * PB_ATTACH.ATTACH_DATA_TYPE
     */
    @ApiModelProperty(value="文档数据类型")
    private String attachDataType;

    /**
     * 文件路径
     *
     * PB_ATTACH.ATTACH_PATH
     */
    @ApiModelProperty(value="文件路径")
    private String attachPath;

    /**
     * 上传时间
     *
     * PB_ATTACH.UPLOAD_TIME
     */
    @ApiModelProperty(value="上传时间")
    private LocalDateTime uploadTime;

    /**
     * 上传用户ID
     *
     * PB_ATTACH.UPLOAD_USER_ID
     */
    @ApiModelProperty(value="上传用户ID")
    private Integer uploadUserId;

    /**
     * 上传IP地址
     *
     * PB_ATTACH.UPLOAD_USER_IP
     */
    @ApiModelProperty(value="上传IP地址")
    private String uploadUserIp;

    /**
     * 备注
     *
     * PB_ATTACH.REMARK
     */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 是否删除
     *
     * PB_ATTACH.DELETE_FLAG
     */
    @ApiModelProperty(value="是否删除")
    private BigDecimal deleteFlag;

    /**
     * 公司编码
     *
     * PB_ATTACH.OU_CODE
     */
    @ApiModelProperty(value="公司编码")
    private String ouCode;


//    @TimeUtil.DATE TODO
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;
}
