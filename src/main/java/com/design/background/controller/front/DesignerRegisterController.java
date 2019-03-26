package com.design.background.controller.front;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.design.background.common.controller.BaseController;
import com.design.background.entity.UserAccount;
import com.design.background.mapper.UserAccountMapper;
import com.design.background.service.MessageManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.design.background.controller.admin.LeadingUserProjectorController;
import com.design.background.entity.DesigntypeExtend;
import com.design.background.entity.LeadingUser;
import com.design.background.entity.Tcode;
import com.design.background.form.DesginerLoginFrom;
import com.design.background.mapper.DesigntypeExtendMapper;
import com.design.background.mapper.LeadingUserMapper;
import com.design.background.mapper.TcodeMapper;
import com.design.background.model.ResultData;
import com.design.background.service.DesignerManagementService;
import com.design.background.service.DesignerRegisterService;
import com.design.background.service.LeadingUserProjectorService;
import com.design.background.util.DesignFileUtils;
import com.design.background.util.SmsUtil;
import com.design.background.util.Utility;

/**
 * @Author：宋博通
 * @Date： 2019/2/15
 * @Description： 这是设计师注册的controller
 */
@Controller
@RequestMapping("/DesignerRegister")
public class DesignerRegisterController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(DesignerRegisterController.class);
	
	@Autowired
	private LeadingUserProjectorService leadingUserProjectorService; // 调用LeadingUserProjectorService管理接口。
	
	//设计师注册
	@Autowired
    private DesignerRegisterService designerRegisterService;

	@Autowired
    private LeadingUserMapper leadingUserMapper;
	
	@Autowired
    private DesigntypeExtendMapper designtypeExtendMapper;
	
	@Autowired
    private TcodeMapper tcodeMapper;
	@Autowired
    private UserAccountMapper  userAccountMapper;
	@Autowired
	private MessageManageService messageManageService;

	//设计师注册提交
    @RequestMapping(value = { "", "/regist" })
    public String registdesigner(Model model)
    {  
    	return "front/center/register-1";
    }
    //设计师验证码发送
    @ResponseBody
    @RequestMapping(value = { "", "/SendCode" })
    public ResultData SendCode(Model model,String phoneNumbers)
    {  
    	Tcode theCode =new Tcode();
    	ResultData resultData = new ResultData();
    	int count = -1;
    	System.out.println(phoneNumbers);
    	// 首先检测该手机是否注册过
    	count = leadingUserMapper.selectCountByTel(phoneNumbers);
    	if(count != 0) {
    		resultData.setMessage("该手机号已经被注册过了");
    		resultData.setCode("111111"); // 111111 表示该手机已经被注册过了
    		return resultData;
    	}
    	// 通过工具类里的方法随机生成一个验证码code值
    	int code = Utility.getRandomByNum(4);
    	// 将code值发送到对应的phoneNumber
    	String retrunMessage = SmsUtil.sendSms(phoneNumbers, code);
    	// 获取当前的时间存储到数据库中
    	java.sql.Timestamp date = new Timestamp(System.currentTimeMillis());
    	theCode.setCreTime(date); // 设置存储的时间
    	theCode.setCodeType(1001); // 设置验证码的类型
    	theCode.setVerCode(code); // 存储生成的验证码
    	theCode.setAccountCode(phoneNumbers); // 存储接收到的手机号码
    	
    	resultData.setMessage(retrunMessage);
    		if("OK".equals(retrunMessage)){
    		// 再将生成的code值存到对应的数据库中
    		tcodeMapper.insertSelective(theCode);
    		resultData.setMessage(retrunMessage);
    		resultData.setCode("000000");
    	} else {
    		resultData.setCode("999999");
    	}
    	return resultData;
    }
    //设计师注册提交
    @ResponseBody
    @RequestMapping(value = { "", "/add" })
    public ResultData registdesigneradd(Model model,DesginerLoginFrom desginerLoginFrom,
										@RequestParam(required=false,value="coverImage") MultipartFile coverImage,
										@RequestParam(required=false,value="typeCode") Long[] typeCode)
    {
    	String type1="";
    	for(int i = 0;i<typeCode.length;i++){
    	    if(typeCode.length==1){
                type1+=String.valueOf(typeCode[i]);
            }
    	    else{
                type1+=String.valueOf(typeCode[i]);
                if(i<typeCode.length-1){
                    type1+=',';
                }
            }
		}
		System.err.println(type1);
		ResultData resultData = new ResultData();
		// 首先判断传过来的验证码是否正确
		String code = Integer.toString(tcodeMapper.selectDesignVerificationCode(desginerLoginFrom.getTel()));
		if(!code.equals(desginerLoginFrom.getCode()) ) {
			resultData.setCode("111111"); 
			resultData.setMessage("验证码错误,请重新输入！");// 如果不等返回code"111111",并返回错误信息
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
		LeadingUser leadingUser = new LeadingUser();
		leadingUser.setAptitude(coverImagePath);
		leadingUser.setTel(desginerLoginFrom.getTel());
		leadingUser.setPassword(desginerLoginFrom.getPassword());
		leadingUser.setUsername(desginerLoginFrom.getName());
		leadingUser.setRoleCode(1001);
		leadingUser.setEmail(desginerLoginFrom.getEmail());
		Date time = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		String create = sdf.format(time);
		Date createtime = new Date(); 
		try {
			createtime = sdf.parse(create);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		leadingUser.setCreateTime(createtime);

		//leadingUser.setPhoto(coverImagePath);
		//注册到数据库
		leadingUserMapper.insertreturnId(leadingUser);
		
		Long ID = leadingUser.getId();
    	DesigntypeExtend designtypeExtend = new DesigntypeExtend();
    	designtypeExtend.setUserId(ID);
    	//新建默认擅长为电
    	designtypeExtend.setCode(type1);
    	designtypeExtendMapper.insert(designtypeExtend);
		/**
		 * 插入user account
		 */
		UserAccount userAccount = new UserAccount()
				.setUserId(ID).setName(leadingUser.getUsername());
		userAccountMapper.insertSelective(userAccount);

		// 调用消息接口
		String userName = desginerLoginFrom.getName();
		Long type = 1000L;
		Map<String,String> para= new HashMap();
		para.put("${username}",userName);
		boolean messageResult = messageManageService.allNoticeAdd(desginerLoginFrom.getTel(),type, para);
		// 调用接口结束


		resultData.setCode("000000");
        return resultData;
    }
}