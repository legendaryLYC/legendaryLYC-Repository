package com.design.background.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.entity.LeadingUser;
import com.design.background.entity.UserAccount;
import com.design.background.form.ProjectLoginForm;
import com.design.background.mapper.LeadingUserMapper;
import com.design.background.mapper.ProjectDetailMapper;
import com.design.background.mapper.TcodeMapper;
import com.design.background.mapper.UserAccountMapper;
import com.design.background.model.ResultData;
import com.design.background.service.ProjectRegisterService;


/**
 *项目方注册的serviceImpl层
 *  时间:2019/2/14
 * 最后修改时间:2019/2/19
 * 注意事项:无
* @author 周天
*
*/

@Service
public class ProjectRegisterServiceImpl implements ProjectRegisterService{

	private static final Logger logger = LoggerFactory.getLogger(ProjectRegisterServiceImpl.class);

	@Autowired
	private LeadingUserMapper leadingUserMapper;
	@Autowired
	TcodeMapper tcodeMapper;
	@Autowired
    private UserAccountMapper  userAccountMapper;
	
	@Override
	public String identifyingCode(String phoneNumber) {
		String codeParse = null;
		Integer code = tcodeMapper.selectByPhoneNumber(phoneNumber);
		try {
			if(null != code) {
				codeParse = code.toString();	
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Integer转化String时失败！！！");
		}
		return codeParse;
	}

	@Override
	public ResultData projectLogin(ProjectLoginForm projectUser) {
		ResultData resultData = new ResultData();
		LeadingUser leadingUser =new LeadingUser();
		leadingUser.setTel(projectUser.getTel());
		leadingUser.setPassword(projectUser.getPassword());
		leadingUser.setEmail(projectUser.getEmail());
        // 给用户的用户名赋值
            StringBuffer prefix = new StringBuffer("PJ_");
            prefix.append(projectUser.getTel());
            leadingUser.setUsername(prefix.toString());   
        
		// 获取当前的时间存储到数据库中
		java.sql.Timestamp date = new Timestamp(System.currentTimeMillis());
		// 项目方注册插入1002
		leadingUser.setRoleCode(1002);
		leadingUser.setCreateTime(date);
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
		/**
		 * 插入user account
		 */
		UserAccount userAccount = new UserAccount()
				.setUserId(leadingUser.getId()).setName(leadingUser.getUsername());
		userAccountMapper.insertSelective(userAccount);
		return resultData;
	}

}
