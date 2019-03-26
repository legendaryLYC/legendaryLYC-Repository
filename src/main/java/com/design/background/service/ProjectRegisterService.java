package com.design.background.service;

import com.design.background.entity.LeadingUser;
import com.design.background.form.ProjectLoginForm;
import com.design.background.model.ResultData;

/**
 * @Author： 周天
 * @Date： 2019/2/15
 * @Description： 项目方注册Service
 */
public interface ProjectRegisterService {

	/**
	 * 通过手机号获取发送的验证码
	 *  时间:2019/2/5
	 * 最后修改时间:2019/2/15
	 * @param String
	 * @return String
	* @author 周天
	*
	*/
	public String identifyingCode (String phoneNumber);
	
	/**
	 * 通过用户填写的注册信息，判断信息合法后，将用户资料录入
	 *  时间:2019/2/15
	 * 最后修改时间:2019/2/15
	 * @param LeadingUser
	 * @return String
	* @author 周天
	*
	*/
	public ResultData projectLogin (ProjectLoginForm projectUser);
	
}
