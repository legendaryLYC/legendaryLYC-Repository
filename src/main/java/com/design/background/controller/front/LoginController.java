package com.design.background.controller.front;

import com.design.background.common.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.background.config.FrontConfig;
import com.design.background.controller.admin.DicProjectComponentController;
import com.design.background.entity.LeadingUser;
import com.design.background.mapper.SysUserMapper;
import com.design.background.model.ResultData;
import com.design.background.service.LoginService;
import com.design.background.util.Utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: design-background
 * @description: 登录
 * @author: 任松
 * @create: 2019-02-15 19：58
 **/
@Controller
@RequestMapping("/")
public class LoginController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	public LoginService loginService;
	@Autowired
	private HttpServletRequest request;
	@ResponseBody
    @RequestMapping("loginpage")
    public ResultData welcome(Model model, @RequestParam(value="tel") String tel, @RequestParam(value="password") String password ) {
    	ResultData resultData = new ResultData();
    	//获取用户登录信息
    	//与数据库进行匹配，验证用户信息是否合法
    	LeadingUser leadingUser = new LeadingUser();
    	//leadingUser.setTel(tel);
    	//leadingUser.setPassword(password);
    	leadingUser=loginService.selectUser(tel,password);
    	
    	//如果用户登录信息合法，将用户信息存储到session中
    	if(leadingUser !=null) {
    		httpSession.setAttribute(FrontConfig.FONT_SESSION, leadingUser);
    		httpSession.setAttribute("id", leadingUser.getId());
    		httpSession.setAttribute("user", leadingUser.getNickname());
    		//httpSession.setAttribute("id", leadingUser.getId());
    		httpSession.setAttribute("code", leadingUser.getRoleCode());
    		httpSession.setAttribute("photo", leadingUser.getPhoto());
    		resultData.setCode("000000");
    		//判断用户第一次访问路径
			String url = (String)httpSession.getAttribute(FrontConfig.FONT_FRIST_URL);
			if(null != url && !"".equals(url)){
				//存储url，并将session值置空。
				resultData.setFristUrl(url);
				httpSession.removeAttribute(FrontConfig.FONT_FRIST_URL);
	
			}
    	}
    	//如果没有对应的用户信息,返回错误信息
    	else {
    		model.addAttribute("error", "用户名或密码不正确");
    		resultData.setCode("111111");
    	}
    	//获取用户登录信息

//    	LeadingUser leadingUser=new LeadingUser();
//    	leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);

    	//leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
//    	logger.info(leadingUser.getEmail());


        return resultData;
     
    }
	
	

    @RequestMapping("front/logout")
    public String Logout(Model model) {
		//将session置空
		httpSession.removeAttribute(FrontConfig.FONT_SESSION);
		httpSession.removeAttribute("user");
		httpSession.removeAttribute("id");
		httpSession.removeAttribute("code");
		httpSession.removeAttribute("photo");
		return "redirect:/";
	}
}
