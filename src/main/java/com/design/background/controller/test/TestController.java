package com.design.background.controller.test;

import com.design.background.common.contants.FileConstants;
import com.design.background.config.FrontConfig;
import com.design.background.entity.LeadingUser;
import com.design.background.entity.ProjectFiles;
import com.design.background.model.FileModel;
import com.design.background.util.DesignFileUtils;
import com.design.background.util.OssUtil;
import com.design.background.util.Utility;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

/**
 *测试入口
 */
@Controller
@RequestMapping("/test")
public class TestController {


    @GetMapping
    public String test(Model model){
        return "test/test";
    }

}
