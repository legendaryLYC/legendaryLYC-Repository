package com.design.background.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.entity.LeadingUser;
import com.design.background.mapper.LeadingUserMapper;
import com.design.background.service.LoginService;
/**
 * 登录功能实现类
 * @author 任松
 *
 */

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	public LeadingUserMapper leadingUserMapper;
	/**
	 *搜索账号是否存在,如果存在，返回该用户，如果不存在，返回null.
	 */
	@Override
	public LeadingUser selectUser(String tel,String password ) {
		// TODO Auto-generated method stub
		return leadingUserMapper.selectUser(tel,password);
		//return key;
	}
	
	
}
