package com.design.background.config.ueditor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.design.background.util.DesignFileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/ueditor")
@Controller
public class UeditorController {
	
	@RequestMapping("/index")
	public String ueditor() {
		return "ueditor";
	}
	
	/**
	 * 获取ueditor配置参数
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ueditorConfig")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {
        return PublicMsg.UEDITOR_CONFIG;
    }
	
	/**
	 * 文件上传
	 * @param upfile
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/imgUpload")
    @ResponseBody
    public Ueditor imgUpload(MultipartFile upfile){
        Ueditor ueditor = new Ueditor();
        //上传文件到阿里云
		String filePath = DesignFileUtils.uploadSingleFile(upfile);
		if(StringUtils.isNoneBlank(filePath)){
			ueditor.setUrl(filePath);
			ueditor.setState(PublicMsg.UeditorMsg.SUCCESS.get());
			ueditor.setTitle(upfile.getName());
			ueditor.setOriginal(upfile.getOriginalFilename());
		}
        return ueditor;
    }


}
