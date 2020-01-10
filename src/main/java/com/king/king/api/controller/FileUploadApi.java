package com.king.king.api.controller;

import com.king.king.api.controller.po.AttachQuery;
import com.king.king.api.controller.vo.PbAttachView;
import com.king.king.api.service.FileUploadService;
import com.king.king.common.enums.B2BOpResult;
import com.king.king.common.enums.FileAttributeCode;
import com.king.king.util.EdpOpException;
import com.king.king.util.FileAttributeCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * description: 文件上传、下载接口.
 *
 * @author 赵乔功   niuchen 20190805修改
 * @date 2019-07-30
 */
@Api(tags = "文件上传、下载api")
@Slf4j
@RestController
@RequestMapping("file")
public class FileUploadApi {

    @Autowired
    private FileUploadService fileUploadService;
    /*
    * 多文件上传接口，包含对文件非空验证、类型验证
    * */
    @ApiOperation("多上传图片")
    @PostMapping(value = "/uploadImages")
    public List<PbAttachView> uploadImage(@RequestParam("filenames") MultipartFile[] multipartFiles,
                                          HttpServletRequest request) throws Exception {
        //上传空文件验证
        if(multipartFiles.length==0)
            throw new EdpOpException(B2BOpResult.NG_NULL_DATE);
        //文件类型验证
        for (MultipartFile multipartFile : multipartFiles) {
            if(!FileAttributeCode.JPG.getFeatureCode().equals(FileAttributeCodeUtil.getFeatureCode(multipartFile))
                &&!FileAttributeCode.PNG.getFeatureCode().equals(FileAttributeCodeUtil.getFeatureCode(multipartFile)))
                throw new EdpOpException(B2BOpResult.NG_FILE);
        }
        return fileUploadService.upload(multipartFiles,request);
    }

    /*
     * 多文件上传接口，包含对文件非空验证、类型验证
     * */
    @ApiOperation("多文件上传")
    @PostMapping(value = "/uploadFiles")
    public List<PbAttachView> uploadFiles(@RequestParam("filenames") MultipartFile[] multipartFiles,
                                            HttpServletRequest request) throws Exception {
        //上传空文件验证
        if(multipartFiles.length==0)
            throw new EdpOpException(B2BOpResult.NG_NULL_DATE);
        return fileUploadService.upload(multipartFiles,request);
    }

    @ApiOperation("单文件上传")
    @PostMapping(value = "/uploadOne")
    public PbAttachView uploadOne(@RequestParam("filename") MultipartFile multipartFile,
                                  HttpServletRequest request) throws Exception {
        //上传空文件验证
        if(multipartFile==null)
            throw new EdpOpException(B2BOpResult.NG_NULL_DATE);
        return fileUploadService.uploadOne(multipartFile,request);
    }



    @ApiOperation("文件下载")
    @GetMapping(value="/download/{attachId}")
    public void downFile(@PathVariable("attachId")Long attachId, HttpServletRequest request, HttpServletResponse response){
        fileUploadService.downFile(attachId,request,response);
    }


    @ApiOperation("刪除附件")
    @PostMapping("/deleteAttach/{id}")
    public void deleteAttach(@PathVariable("id") Long id){
        fileUploadService.deleteAttach(id);
    }


    @ApiOperation("附件信息查询")
    @GetMapping("/selectAttach")
    public List<PbAttachView> findAttach(AttachQuery attachQuery){
        return fileUploadService.findAttach(attachQuery);
    }

}
