package com.design.background.controller.front;

import java.sql.Timestamp;

import com.design.background.common.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.design.background.entity.Tcode;
import com.design.background.form.ProjectLoginForm;
import com.design.background.mapper.LeadingUserMapper;
import com.design.background.mapper.SysUserMapper;
import com.design.background.mapper.TcodeMapper;
import com.design.background.model.ResultData;
import com.design.background.service.ProjectRegisterService;
import com.design.background.util.DesignFileUtils;
import com.design.background.util.SmsUtil;
import com.design.background.util.Utility;



/**
 * @Author：周天
 * @Date： 2019/2/13
 * @Description：项目方注册的controller层
 */
@Controller
@Transactional
@RequestMapping("/front")
public class ProjectRegisterController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectRegisterController.class);
	
	@Autowired
	ProjectRegisterService projectRegisterService;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	LeadingUserMapper leadingUserMapper;
	@Autowired
	TcodeMapper tcodeMapper;
	
	
    /**
     * @Description: 展示注册导航页面
     * @Param: []
     * @return: java.lang.String
     * @Author: 周天
     * @Date: 2019/2/13
     */
	@RequestMapping(value = {"","/"})
	public String showStwichLogin (Model model) {
        return "front/center/guide";
    }
	
    /**
     * @Description: 展示项目方注册的页面
     * @Param: []
     * @return: java.lang.String
     * @Author: 周天
     * @Date: 2019/2/13
     */
	@RequestMapping(value = "/register")
	public String showProjectLogin (Model model) {
        return "front/center/register";
    }
	
    /**
     * @Description: 进行注册逻辑处理的方法
     * @Param: ProjectLoginForm projectLoginForm
     * @return: ResultData
     * @Author: 周天
     * @Date: 2019/2/15
     */
	@ResponseBody
	@RequestMapping(value = "/register/projectLogin" ,produces = "application/json;charset=utf-8",method ={RequestMethod.POST})
	public ResultData projectLogin  ( ProjectLoginForm projectLoginForm , 
									  @RequestParam(required=false,value="coverImage") MultipartFile coverImage) {
		ResultData resultData = new ResultData();
		// 判断当前邮箱是否注册过
		if( null != projectLoginForm.getEmail() && "".equals(projectLoginForm.getEmail())) {
			int count = -1;
			count = leadingUserMapper.selectCountByEmail(projectLoginForm.getEmail());
			if(count != 0) {
				resultData.setCode("999999"); 
				resultData.setMessage("该邮箱地址已经注册过了,请重新输入！");// 如果地址重复返回code"999999",并返回错误信息
				return resultData;
			}
		}
		// 首先判断传过来的验证码是否正确
		if(null != projectRegisterService.identifyingCode(projectLoginForm.getTel()) && !(projectLoginForm.getCode().equals(projectRegisterService.identifyingCode(projectLoginForm.getTel()))) ) {
			resultData.setCode("999999"); 
			resultData.setMessage("验证码错误,请重新输入！");// 如果不等返回code"999999",并返回错误信息
			return resultData;
		}
		// 再判断两次输入的密码是否一致
		if(!projectLoginForm.getPassword().equals(projectLoginForm.getConfirmPassword())) {
			resultData.setCode("999999"); 
			resultData.setMessage("两次输入的密码不一致,请重新输入！");// 如果不一致返回code"999999",并返回错误信息
			return resultData;
		}
		// 将文件上传到阿里云
		String coverImagePath = null;
        if(coverImage != null){
            //上传到阿里云
            coverImagePath = DesignFileUtils.uploadSingleFile(coverImage);
            if( coverImagePath == null){
    			resultData.setCode("999999"); 
    			resultData.setMessage("上传文件失败,请重新上传！");// 如果上传失败返回code"999999",并返回错误信息
    			return resultData;
            }
        }
		// 然后再调用service层方法将用户信息录入
		resultData = projectRegisterService.projectLogin(projectLoginForm);
        return resultData;
    }
    /**
     * @Description: 进行注册逻辑处理的方法
     * @Param: ProjectLoginForm projectLoginForm
     * @return: ResultData
     * @Author: 周天
     * @Date: 2019/2/15
     */
	@ResponseBody
	@RequestMapping(value = "/register/getPhoneCode" ,produces = "application/json;charset=utf-8",method ={RequestMethod.POST})
	public ResultData getPhoneCode  (String phoneNumbers) {
		Tcode theCode =new Tcode();
		ResultData resultData = new ResultData();
		int code = 0;
		String retrunMessage = "";
		int count = -1;
		// 首先检测该手机是否注册过
		count = leadingUserMapper.selectCountByTel(phoneNumbers);
		if(count != 0) {
			resultData.setMessage("该手机号已经被注册过了");
			resultData.setCode("111111"); // 111111 表示该手机已经被注册过了
			return resultData;
		}
		// 通过工具类里的方法随机生成一个验证码code值
		code = Utility.getRandomByNum(4);
		// 将code值发送到对应的phoneNumber
		retrunMessage = SmsUtil.sendSms(phoneNumbers, code);
		// 获取当前的时间存储到数据库中
		java.sql.Timestamp date = new Timestamp(System.currentTimeMillis());
		theCode.setCreTime(date); // 设置存储的时间
		theCode.setCodeType(1000); // 设置验证码的类型
		theCode.setVerCode(code); // 存储生成的验证码
		theCode.setAccountCode(phoneNumbers); // 存储接收到的手机号码
		if("OK".equals(retrunMessage)){
			// 再将生成的code值存到对应的数据库中
			tcodeMapper.insertSelective(theCode);
			resultData.setMessage(retrunMessage);
			resultData.setCode("000000");
		} else {
			resultData.setMessage(retrunMessage);
			resultData.setCode("999999");
		}
        return resultData;
    }
	
	
}