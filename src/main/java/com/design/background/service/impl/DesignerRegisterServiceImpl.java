package com.design.background.service.impl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.controller.admin.LeadingUserProjectorController;
import com.design.background.entity.LeadingUser;
import com.design.background.form.DesginerLoginFrom;
import com.design.background.mapper.LeadingUserMapper;
import com.design.background.mapper.TcodeMapper;
import com.design.background.model.ResultData;
import com.design.background.service.DesignerRegisterService;

/**
 * @Author：Song
 * @Date： 2019/2/13
 * @Description： This is a class
 */
@Service("DesignerRegisterService")
public class DesignerRegisterServiceImpl implements DesignerRegisterService {
	private static final Logger logger = LoggerFactory.getLogger(LeadingUserProjectorController.class);
    @Autowired
    private LeadingUserMapper leadingUserMapper;
    
    @Autowired
    private TcodeMapper tcodeMapper;

	@Override
	public String identifyingCode(String phoneNumber) {
		String codeParse = null;
		Integer code = tcodeMapper.selectDesignVerificationCode(phoneNumber);
		try {
			codeParse = code.toString();
		} catch (Exception e) {
			logger.error("Integer转化String时失败！！！");
		}
		return codeParse;
	}

	@Override
	public ResultData insertDesigner(DesginerLoginFrom designUser) {
		// TODO Auto-generated method stub
		
		ResultData resultData = new ResultData();
		LeadingUser leadingUser =new LeadingUser();
		leadingUser.setTel(designUser.getTel());
		leadingUser.setRoleCode(1001);
		Calendar cale = Calendar.getInstance();
        // 将 Calendar 类型转换成 Date 类型
		leadingUser.setPassword(designUser.getPassword());
        Date createtime = cale.getTime();
		leadingUser.setCreateTime(createtime);
		int flag = 0;
		try {
			flag = leadingUserMapper.insertSelective(leadingUser);
			if(flag > 0) {
				resultData.setCode("000000");
				resultData.setMessage("注册成功！");
			}else {
				resultData.setCode("999999");
				resultData.setMessage("注册失败,请联系工作人员！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return resultData;
	}
}