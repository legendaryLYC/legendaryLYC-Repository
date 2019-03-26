package com.design.background.controller.file;

import com.aliyun.oss.model.OSSObject;
import com.design.background.common.contants.FileConstants;
import com.design.background.config.FrontConfig;
import com.design.background.entity.LeadingUser;
import com.design.background.entity.ProjectFiles;
import com.design.background.model.FileModel;
import com.design.background.util.DesignFileUtils;
import com.design.background.util.OssUtil;
import com.design.background.util.Utility;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName: UploadController
 * @Description: 文件上传下载控制类
 * @Author: lxt
 * @Date: 2019-02-15 14:41
 * @Version 1.0
 **/
@Controller
@RequestMapping("/file")
public class FileController {

    /**
     * @Title: projectsinglefile
     * @Description: 判断是否符合下载的条件
     * @Author: 李永成
     * @param: url
     * @param: fileName
     * @Date: 2019-02-18 19:24
     */
    @Autowired
    private HttpSession httpSession;
    private final static Logger logger = LoggerFactory.getLogger(FileController.class);
    /**
     * @Title: uploadSingleFile
     * @Description: 单文件上传
     * @Author: lxt
     * @param: file
     * @Date: 2019-02-15 14:50
     * @return: java.lang.String 成功返回 文件路径，失败返回null
     * @throws:
     */
    @PostMapping("/uploadSingleFile")
    @ResponseBody
    public String uploadSingleFile(MultipartFile file){
        return DesignFileUtils.uploadSingleFile(file);
    }

    @PostMapping("/uploadSingleFile2Pro")
    public String uploadSingleFile2Pro(Model model,@RequestParam("file")MultipartFile file){
        if (file.isEmpty()) {
            return "test/test-file";
        }

        String fileName = file.getOriginalFilename();
        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/upload/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            model.addAttribute("result","上传成功！");
            return "test/test-file";
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("result","上传失败！");
        return "test/test-file";
    }

   /**
    * @Title: downloadSingleFile
    * @Description: 下载单个文件到本地
    * @Author: lxt
    * @param: url
    * @param: fileName
    * @param: request
    * @param: response
    * @Date: 2019-02-18 19:24
    * @throws:
    */
    @PostMapping("/downloadSingleFile")
    public void downloadSingleFile(String url,String fileName,HttpServletRequest request,
                                   HttpServletResponse response){
        // 浏览器下载阿里云文件到本地
        OssUtil.download2FileByStream(url,fileName,response);
    }

    /**
     * @Title: projectsinglefile
     * @Description: 下载项目的单个文件到本地
     * @Author: 李永成
     * @param: url
     * @param: fileName
     * @Date: 2019-02-18 19:24
     */
    @GetMapping("/projectsinglefile")
    public String projectSingleFile(String url,String fileName,HttpServletRequest request,
                                   HttpServletResponse response){
        LeadingUser leadingUser=new LeadingUser();
        leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
        if(leadingUser==null){
            return "redirect:/loginpage";
        }
        // 浏览器下载阿里云文件到本地
        OssUtil.download2FileByStream(url,fileName,response);
        return "2";
    }
    /**
     * @Title: downloadMultipartFileWithZip
     * @Description: 批量下载，将文件打包成zip格式压缩包
     * @Author: lxt
     * @param: urlList
     * @param: zipName
     * @param: request
     * @param: response
     * @Date: 2019-02-18 23:24
     * @throws:
     */
    @PostMapping("/downloadMultipartFileWithZip")
    public void downloadMultipartFileWithZip(FileModel fileModel,String zipName, HttpServletRequest request,
                                             HttpServletResponse response){
        // 下载阿里云文件到本地
        File zipFile = DesignFileUtils.downloadMultipartFileWithZip(fileModel.getUrlList(),zipName);
        try(
                BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(zipFile));
                ){

            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            if(StringUtils.isNoneBlank(zipName) && !DesignFileUtils.isFileBySuffix(zipName,FileConstants.SUFFIX_ZIP)){
                //文件名称存在 但后缀名不是zip
                zipName = zipName + FileConstants.SUFFIX_ZIP;
            }else{
                // 压缩包默认名称6位随机字符串
                zipName = StringUtils.isBlank(zipName) ? Utility.getRandomStrByNum(6)+ FileConstants.SUFFIX_ZIP : zipName;
            }
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(zipName, "UTF-8"));
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = inputStream.read(buff, 0, buff.length))){
                outputStream.write(buff, 0, bytesRead);
            }
            outputStream.flush();
        } catch (Exception e) {
            logger.error("下载异常！", e);
        }finally {
            //下载完成，删除临时文件
            zipFile.delete();
        }
    }

    @GetMapping("/test")
    public String test(Model model){
//        throw new RuntimeException("test");
        ProjectFiles projectFiles = new ProjectFiles();
        projectFiles.setFilename("testName");
        projectFiles.setFilePath("testPath");
        model.addAttribute("file",projectFiles);
        return "test/test-file";
    }

}
