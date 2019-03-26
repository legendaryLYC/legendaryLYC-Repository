package com.design.background.service;

import com.design.background.form.DesginerLoginFrom;
import com.design.background.model.ResultData;

/**
 * @Author： 宋博通
 * @Date： 2019/2/13
 * @Description： This is a class
 */
public interface DesignerRegisterService {
	//添加注册设计师
	ResultData insertDesigner(DesginerLoginFrom designUser);
	
	//手机验证
	public String identifyingCode (String phoneNumber);

}
