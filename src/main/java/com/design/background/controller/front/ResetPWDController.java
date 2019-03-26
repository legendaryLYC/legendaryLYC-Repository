package com.design.background.controller.front;

import com.design.background.common.controller.BaseController;
import com.design.background.controller.admin.CapitalAuditController;
import com.design.background.entity.LeadingUser;
import com.design.background.entity.Tcode;
import com.design.background.model.ResultData;
import com.design.background.service.ResetPWDService;
import com.design.background.util.CodeTypeUtil;
import com.design.background.util.SendMailUtils;
import com.design.background.util.SmsUtil;
import com.design.background.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@Controller
@RequestMapping("/resetPWD")
public class ResetPWDController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CapitalAuditController.class);
	@Autowired
	private ResetPWDService resetPWDService;
	
	
	@RequestMapping("/resetPWD")
	public String resetPWD(Model model, HttpServletRequest req) {

	    return "front/resetPWD";
	}
	
	// 进入电话找回密码页
	@RequestMapping("/inphone")
	public String iphone(Model model, HttpServletRequest req) {

	    return "front/phone";
	}
	// 进入邮箱找回密码页
	@RequestMapping("/inmail")
	public String email(Model model, HttpServletRequest req) {

	    return "front/mail";
	}
	// 进入重置密码成功页面
	@RequestMapping("/insuccess")
	public String success(Model model, HttpServletRequest req) {

	    return "front/success";
	}
	@RequestMapping("/updataTel")
	public String updataByTel(Model model,
			@RequestParam(value = "tel", required = false) String tel,HttpServletRequest request,
			@RequestParam(value = "password",required = false) String password,
			@RequestParam(value = "password2",required = false) String password2,
			@RequestParam(value = "telCode",required = false) Integer telCode){  
			System.out.println(tel);
			System.out.println(telCode);
			System.out.println(password);
			System.out.println(password2);
			System.out.println("====++++=====");
			LeadingUser leadingUser = resetPWDService.selectByTel(tel);
			System.out.println(leadingUser);
			if(leadingUser == null) {
				String flag = "手机号不存在";
				model.addAttribute("flag",flag);
				return "front/phone";
			}
			Tcode tcode = new Tcode();
			tcode.setAccountCode(tel);
			tcode.setVerCode(telCode);
			tcode.setCodeType(1003);
			tcode = resetPWDService.selectByCode(tcode);
			if(tcode == null) {
				String flag1 = "验证码错误";
				model.addAttribute("flag1",flag1);
				return "front/phone";
			}
			if(!telCode.equals(tcode.getVerCode())) {
				String flag1 = "验证码错误";
				model.addAttribute("flag1",flag1);
				return "front/phone";
			}
			leadingUser.setPassword(password);
			resetPWDService.updateByTel(leadingUser);
			

            return "front/login";
	    
    }
	@RequestMapping("/updataMail")
	public String updataByMail(Model model,
			@RequestParam(value = "email", required = false) String email,HttpServletRequest request,
			@RequestParam(value = "password",required = false) String password,
			@RequestParam(value = "password2",required = false) String password2,
			@RequestParam(value = "emailCode",required = false) Integer emailCode){  
			LeadingUser leadingUser = resetPWDService.selectByMail(email);
			if(leadingUser == null) {
				String flag = "邮箱不存在";
				model.addAttribute("flag",flag);
				return "front/mail";
			}
			Tcode tcode = new Tcode();
			System.out.println(email);
			System.out.println(emailCode);
			tcode.setAccountCode(email);
			tcode.setVerCode(emailCode);

			tcode.setCodeType(CodeTypeUtil.Forget_EMAIL_CODETYPT);
			tcode = resetPWDService.selectByCode(tcode);
			if(tcode == null) {
				String flag1 = "验证码错误";
				model.addAttribute("flag1",flag1);
				return "front/mail";
			}
			if(!emailCode.equals(tcode.getVerCode())) {
				String flag1 = "验证码错误";
				model.addAttribute("flag1",flag1);
				return "front/mail";
			}
				leadingUser.setPassword(password);
				resetPWDService.updateByTel(leadingUser);
				
		
            return "front/login";
	    
    }
	
	// 获取邮箱验证码
	@ResponseBody
	@RequestMapping("/obtainMailCode")
	public ResultData  obtainCode(Model model,@RequestParam(value = "email", required = false) String email){
		ResultData result = new ResultData();
		//验证邮箱是否存在
		LeadingUser leadingUser = resetPWDService.selectByMail(email);
		
		if(leadingUser == null) {
			result.setCode("2"); 
			return result;
		}
		
		java.sql.Timestamp date = new Timestamp(System.currentTimeMillis());
		

		int code = SendMailUtils.getMailCode();
	    boolean flag = SendMailUtils.sendVeriCode(email,code);
	    System.out.println("----------------code->"+code);
	    System.out.println("----------------flag->"+flag);
	    Tcode code1 = new Tcode();
	    code1.setAccountCode(email);
	    code1.setVerCode(code);
	    code1.setCreTime(date);
	    code1.setCodeType(1002);
	    int flagCode = 0;
	    flagCode = resetPWDService.insertCode(code1);
	    System.out.println(flagCode);
		System.out.println(email);
		if(flagCode == 1) {
		result.setCode("1"); 
		return result;
		}else {
			result.setCode("0"); 
			return result;
		}
	}
	// 获取短信验证码
	@ResponseBody
	@RequestMapping("/obtainTelCode")
	public ResultData  obtainTelCode(Model model,@RequestParam(value = "tel", required = false) String tel){
		
		java.sql.Timestamp date = new Timestamp(System.currentTimeMillis());
		
		   // 通过工具类里的方法随机生成一个验证码code值
			int	code = Utility.getRandomByNum(4);
			// 将code值发送到对应的phoneNumber
			String retrunMessage = SmsUtil.sendSms(tel, code);
			System.out.println(code);
		
		    Tcode code1 = new Tcode();
		    code1.setAccountCode(tel);
		    code1.setVerCode(code);
		    code1.setCreTime(date);
		    // 忘记密码手机号找回验证码标识为1003
		    code1.setCodeType(CodeTypeUtil.Forget_TEL_CODETYPR);
		    int flagCode = 1;
		    flagCode = resetPWDService.insertCode(code1);
		    System.out.println(flagCode);
			System.out.println(tel);
			ResultData result = new ResultData();
			if(flagCode == 1) {
			result.setCode("1"); 
			return result;
			}else {
				result.setCode("0");
				return result;
			}
		}
	
	
}
