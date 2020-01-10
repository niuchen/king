package com.king.king.api.controller.vo;

import com.king.king.api.enty.PbAttach;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * description: .
 *
 * @author 赵乔功
 * @date 2019-08-06
 */

@Data
@ToString(callSuper = true)
@EqualsAndHashCode
@ApiModel("附件信息view PbAttachView")
public class PbAttachView {
    /**
     * 主键
     *
     * PB_ATTACH.ID
     */
    @ApiModelProperty(value="附件id")
    private Long id;

    /**
     * 所属类别id
     * 因项目需求、部分用所属类别code关联、例如配货单code
     */
    @ApiModelProperty("所属类别id")
    private String entryId;
    /**
     * 实体类别
     *
     * PB_ATTACH.ENTRY_CLS
     */
    @ApiModelProperty(value="所属类别")
    private String entryCls;

    /**
     * 文件名
     *
     * PB_ATTACH.ATTACH_NAME
     */
    @ApiModelProperty(value="文件存储时唯一名")
    private String uniqueName;

    /**
     * 类型2
     *
     * PB_ATTACH.ATTACH_TYPE2
     */
    @ApiModelProperty(value="附件原始名 ")
    private String originalName;

    /**
     * 类型3
     *
     * PB_ATTACH.ATTACH_TYPE3
     */
//    @ApiModelProperty(value="附件绝对路径")
//    @Ignore
//    private String attachType3;

    /**
     * 文档数据类型
     *
     * PB_ATTACH.ATTACH_DATA_TYPE
     */
    @ApiModelProperty(value="附件类型")
    private String attachDataType;

    /**
     * 文件路径
     *
     * PB_ATTACH.ATTACH_PATH
     */
    @ApiModelProperty(value="文件相对路径")
    private String attachPath;

    /**
     * 上传时间
     *
     * PB_ATTACH.UPLOAD_TIME
     */
    @ApiModelProperty(value="上传时间")
    private LocalDateTime uploadTime;

    public static PbAttachView  transformToView(PbAttach pbAttach){
        PbAttachView pbAttachView = new PbAttachView();
        if(pbAttach!=null){
            pbAttachView.setId(pbAttach.getId());
            pbAttachView.setUniqueName(pbAttach.getAttachName());
            pbAttachView.setAttachPath(pbAttach.getAttachPath());
            pbAttachView.setOriginalName(pbAttach.getAttachType2());
//        pbAttachView.setAttachType3(pbAttach.getAttachType3());
            pbAttachView.setEntryId(pbAttach.getEntryId());
            pbAttachView.setEntryCls(pbAttach.getEntryCls());
            pbAttachView.setUploadTime(pbAttach.getUploadTime());
            pbAttachView.setAttachDataType(pbAttach.getAttachDataType());
        }
        return pbAttachView;
    }

}
