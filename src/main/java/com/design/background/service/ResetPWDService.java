package com.design.background.service;


import com.design.background.entity.LeadingUser;
import com.design.background.entity.Tcode;


public interface ResetPWDService {
	/**
	 * 根据phone查新用户信息
	 * @param tel
	 * @return
	 */
	LeadingUser selectByTel(String tel);
	/**
	 * 根据mail查新用户信息
	 * @param tel
	 * @return
	 */
	LeadingUser selectByMail(String mail);
	/**
	 * 根据phone修改用户密码
	 * @param phone
	 * @return
	 */
	
	int updateByTel(LeadingUser tel);
	
	int insertCode(Tcode record);
	
	
	
	Tcode selectByCode(Tcode record);
	
	
}
