package com.king.king.api.service;

import com.king.king.api.controller.po.AttachPayload;
import com.king.king.api.controller.po.AttachQuery;
import com.king.king.api.controller.vo.PbAttachView;
import com.king.king.api.enty.PbAttach;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * description: .
 *
 * @author 赵乔功
 * @date 2019-08-02
 */
public interface FileUploadService {
    /*
     * 多文件上传
     * */
    List<PbAttachView> upload(MultipartFile[] multipartFiles,
                              HttpServletRequest request) throws Exception;
    /*
     * 文件下载
     * */
    void downFile(Long path, HttpServletRequest request, HttpServletResponse response);
    /*
     * 单文件上传
     * */
    PbAttachView uploadOne(MultipartFile multipartFile, HttpServletRequest request) throws IOException;
    /*
     * 通过附件表主键id查询附件信息
     * */
    PbAttachView findAttachById(Long attachId);
    /*
     * 通过附件表主键id删除附件信息
     * */
    void deleteAttach(Long id);
    /*
     *  更新附件表信息
     * */
    void updateAttach(AttachPayload attachPayload);
    /*
     * 向附件表添加信息
     * */
    Long insertAttach(PbAttach tPbAttach);
    /*
     * 通过附件类型EntryCls、所属类别EntryId查询附件
     * */
    List<PbAttachView>  findAttach(AttachQuery attachQuery);
    /*
     * 查询所有附件信息
     * */
    List<PbAttachView> findAttach();

    /*
     * 上传时根据附件id[]：attachIds、附件类型EntryCls、所属类别EntryId
     * 更新附件表，建立模块与附件绑定关系
     * */
    void bindingAttachEntry(List<Long> attachIds, String id, String attachEntryClsEnum);
}
