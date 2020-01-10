package com.king.king.api.service;

import com.king.king.api.controller.domain.EdpUser;
import com.king.king.api.controller.po.AttachPayload;
import com.king.king.api.controller.po.AttachQuery;
import com.king.king.api.controller.vo.PbAttachView;
import com.king.king.api.enty.PbAttach;
import com.king.king.api.mapper.AttachMapper;
import com.king.king.cig.security.EdpPrincipalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * description: .
 *
 * @author 赵乔功
 * @date 2019-08-02
 */
@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private AttachMapper attachMapper;

    //@Autowired todo
    //private EdpPrincipalService principalService; todo

    @Autowired
    private Environment environment;
    /*
    *  多文件上传
    * */
    @Override
    public List<PbAttachView> upload(MultipartFile[] multipartFiles,
                                     HttpServletRequest request) throws Exception {
        //获得当前登录人信息
        //EdpUser user = principalService.user(); todo

        //String entCode = user.getEntCode(); todo

        //String savePath = this.savePath(user.getEmpCode()); todo

        /*---实现方式一、从配置文件读取文件上传目录---*/
//        String uploadFileStorage = environment.getProperty("storage.location");
//
//        File filePath =   new File(uploadFileStorage+savePath);
        /*-----------------END----------------------*/

        /*---实现方式二、获得项目部署在服务器下的上两级目录---*/
        String dirPath = request.getServletContext().getRealPath("/");

        //File filePath = new File(new File(new File(new File(dirPath).getParent()).getParent())+"/"+savePath); todo
        /*-----------------END----------------------*/

        List<PbAttachView> pbAttachViews = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            //文件原始名称
            String originalFilename = multipartFile.getOriginalFilename();
            //文件类型
            String fileClass = originalFilename.substring(originalFilename.lastIndexOf("."));

//            if (!filePath.exists()) todo
//                filePath.mkdirs();
            //设置新文件名字
            String newFilename = UUID.randomUUID() + fileClass;
            //文件存储路径绝对路径
//            String path = filePath + "/" + newFilename; todo
            //存储文件
//            multipartFile.transferTo(new File(path)); todo
            //新建附件对象,在附件表存储上传文件信息
            PbAttach attach = new PbAttach();
            //文件名
            attach.setAttachName(newFilename);
            //文件类型
            attach.setAttachDataType(Files.probeContentType(Paths.get(multipartFile.getOriginalFilename())));
            //文件路径相对路径
            //attach.setAttachPath(savePath + newFilename); todo
            //公司编码
            //attach.setOuCode(entCode); todo
            attach.setAttachType2(originalFilename);//存储文件原始名称
//            attach.setAttachType3(path);//存储文件绝对路径 todo
            attach.setUploadTime(LocalDateTime.now());
            this.attachMapper.insert(attach);
            PbAttach pbAttach = attachMapper.findById(attach.getId());
            pbAttachViews.add(PbAttachView.transformToView(pbAttach));
        }
        return pbAttachViews;
    }

    /*
     *文件存储路径 /b2bfile/用户名/年_月/
     * */
    private  String savePath(String currentUsercode) {
        Calendar cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        return "b2bfile/" + currentUsercode + "/" + year + "_" + month + "/";
    }

    /***20190805 niuchen完善**/
    @Override
    public void downFile(Long attachId, HttpServletRequest request, HttpServletResponse response) {
        //文件以下载方式打开，待解决乱码问题
        PbAttach pbAttach = attachMapper.findById(attachId);
        String path=null;
        String fileDownloadName=null;
        String suffix=null;
        if(pbAttach!=null){
            path = pbAttach.getAttachType3();
            fileDownloadName = pbAttach.getAttachType2();//原始文件
            suffix = pbAttach.getAttachType2().substring(pbAttach.getAttachType2().lastIndexOf("."));
        }

        InputStream is = null;
        OutputStream os = null;
        File file;

        try {
            file = new File(path);
            if (!file.exists()) {
                throw new RuntimeException("文件不存在：" + file.getAbsolutePath());
            }
            is = new BufferedInputStream(new FileInputStream(file));

            if (".xls".equalsIgnoreCase(suffix) || ".xlsx".equalsIgnoreCase(suffix)) {
                response.setContentType("application/vnd.ms-excel");
            } else if (".doc".equalsIgnoreCase(suffix) || ".docx".equalsIgnoreCase(suffix)) {
                response.setContentType("application/vnd.msword");
            } else if (".exe".equalsIgnoreCase(suffix)) {
                response.setContentType("application/octet-stream");
            } else if (".jpe".equalsIgnoreCase(suffix) || ".jpeg".equalsIgnoreCase(suffix) || ".jpg".equalsIgnoreCase(suffix)) {
                response.setContentType("image/jpeg");
            } else if (".png".equalsIgnoreCase(suffix)) {
                response.setContentType("image/png");
            } else if (".gif".equalsIgnoreCase(suffix)) {
                response.setContentType("image/gif");
            } else if (".mp3".equalsIgnoreCase(suffix)) {
                response.setContentType("audio/x-mpeg");
            } else if (".mp4".equalsIgnoreCase(suffix) || ".mpg4".equalsIgnoreCase(suffix)) {
                response.setContentType("video/mp4");
            } else if (".wm".equalsIgnoreCase(suffix)) {
                response.setContentType("video/x-ms-wm");
            } else if (".wmv".equalsIgnoreCase(suffix)) {
                response.setContentType("audio/x-ms-wmv");
            } else if (".rm".equalsIgnoreCase(suffix) || ".rmvb".equalsIgnoreCase(suffix)) {
                response.setContentType("audio/x-pn-realaudio");
            } else if (".xml".equalsIgnoreCase(suffix)) {
                response.setContentType("text/xml");
            } else if (".gz".equalsIgnoreCase(suffix)) {
                response.setContentType("application/x-gzip");
            } else if (".gtar".equalsIgnoreCase(suffix)) {
                response.setContentType("application/x-gtar");
            } else if (".tar".equalsIgnoreCase(suffix) || ".taz".equalsIgnoreCase(suffix)) {
                response.setContentType("application/x-tar");
            } else if (".rar".equalsIgnoreCase(suffix)) {
                response.setContentType("application/x-rar-compressed");
            } else if (".zip".equalsIgnoreCase(suffix)) {
                response.setContentType("application/zip");
            }else if (".md".equalsIgnoreCase(suffix)) {
                response.setContentType("MIME/content-type");
            } else {
                response.setContentType("multipart/form-data");
            }
            //文件名编码，解决中文乱码问题
            String userAgent = request.getHeader("User-Agent").toLowerCase();
            if (userAgent.contains("msie")
                || userAgent.contains("trident")
                || userAgent.contains("like gecko")
                || userAgent.contains("edge")) {//IE浏览器

                fileDownloadName = URLEncoder.encode(fileDownloadName, "UTF-8");
                fileDownloadName = fileDownloadName.replaceAll("\\+", "%20");//处理文件名多余的加号（+）
            } else {//其它浏览器
                fileDownloadName = new String(fileDownloadName.getBytes("UTF-8"), "ISO-8859-1");
            }

            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=\"" + fileDownloadName + "\"");
            response.addHeader("Content-Length", "" + file.length());
            os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    * 单文件上传测试
    * */
    @Override
    public PbAttachView uploadOne(MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        //获得当前登录人信息
        //EdpUser user = principalService.user();  todo

        //String entCode = user.getEntCode(); todo

        //文件按用户日期划分存贮位置
        // String savePath = this.savePath(user.getEmpCode()); todo

        /*---实现方式一、从配置文件读取文件上传目录---*/
//        String uploadFileStorage = environment.getProperty("storage.location");
//
//        File filePath = new File(uploadFileStorage+savePath);
        /*-----------------END----------------------*/

       /*---实现方式二、获得项目部署在服务器下的上两级目录---*/
        String dirPath = request.getServletContext().getRealPath("/");
        //获得webapp上两级目录
//        File filePath = new File(new File(new File(new File(dirPath).getParent()).getParent())+"/"+savePath); todo
         /*-----------------END----------------------*/

        //文件原始名称
        String originalFilename = multipartFile.getOriginalFilename();
        //文件类型
        String fileClass = originalFilename.substring(originalFilename.lastIndexOf("."));

//        if (!filePath.exists()) todo
//            filePath.mkdirs(); todo
        //设置新文件名字
        String newFilename = UUID.randomUUID() + fileClass;
        //文件存储路径绝对路径（含文件名）
//        String path = filePath + "/" + newFilename; todo
        //存储文件
//        multipartFile.transferTo(new File(path)); todo
        //新建附件对象,存储上传文件信息
        PbAttach attach = new PbAttach();
        //文件名
        attach.setAttachName(newFilename);
        //文件类型
        String dataType = Files.probeContentType(Paths.get(multipartFile.getOriginalFilename()));
        attach.setAttachDataType(dataType);
        //attach.setAttachPath(savePath + newFilename);//文件路径相对路径 todo
        //attach.setOuCode(entCode);//公司编码 todo
        attach.setAttachType2(originalFilename);//存储文件原始名称
//        attach.setAttachType3(path);//存储文件绝对路径 todo
        attach.setUploadTime(LocalDateTime.now());
        this.attachMapper.insert(attach);
        PbAttach pbAttach = this.attachMapper.findById(attach.getId());
        return PbAttachView.transformToView(pbAttach);
    }

    @Override
    public PbAttachView findAttachById(Long id) {
        PbAttach pbAttach = attachMapper.findById(id);
        PbAttachView pbAttachView=null;
        if(pbAttach!=null)
            pbAttachView = PbAttachView.transformToView(pbAttach);
        return pbAttachView;
    }

    @Override
    public void deleteAttach(Long id) {
        attachMapper.deleteById(id);
    }

    @Override
    public void updateAttach(AttachPayload attachPayload) {
        attachMapper.update(attachPayload);
    }

    @Override
    public Long insertAttach(PbAttach attach) {
        attachMapper.insert(attach);
        return attach.getId();
    }

    @Override
    public List<PbAttachView> findAttach(AttachQuery attachQuery) {
        //attachQuery.setEntCode(principalService.user().getEntCode()); todo
        List<PbAttach> pbAttaches = attachMapper.findAllByEidClass(attachQuery);
        List<PbAttachView> pbAttachViews = new ArrayList<>();
        for (PbAttach pbAttach : pbAttaches) {
            PbAttachView pbAttachView = PbAttachView.transformToView(pbAttach);
            pbAttachViews.add(pbAttachView);
        }
        return pbAttachViews;
    }

    @Override
    public List<PbAttachView> findAttach() {
        List<PbAttach> pbAttaches = attachMapper.findAll();
        List<PbAttachView> pbAttachViews = new ArrayList<>();
        if (pbAttaches.size()!=0){
            for (PbAttach pbAttach : pbAttaches) {
                PbAttachView pbAttachView = PbAttachView.transformToView(pbAttach);
                pbAttachViews.add(pbAttachView);
            }
        }
        return pbAttachViews;
    }

    /*
    * 上传时根据附件id[]：attachIds、附件类型EntryCls、所属类别Entry Id或code
    * 更新附件表，建立绑定关系
    * */
    @Override
    public void bindingAttachEntry(List<Long> attachIds,String EntryId,String attachEntryClsEnum){
        if(attachIds!=null){
            for (Long attachId : attachIds) {
                AttachPayload attachPayload=new AttachPayload();
                attachPayload.setId(attachId);
                attachPayload.setEntryId(EntryId);
                attachPayload.setEntryCls(attachEntryClsEnum);
                this.updateAttach(attachPayload);
            }
        }
    }
}
