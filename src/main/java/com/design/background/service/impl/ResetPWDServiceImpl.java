package com.design.background.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.design.background.entity.LeadingUser;
import com.design.background.entity.Tcode;
import com.design.background.mapper.LeadingUserMapper;
import com.design.background.mapper.TcodeMapper;
import com.design.background.service.ResetPWDService;


@Service("resetPWDService")
public class ResetPWDServiceImpl implements ResetPWDService{

	@Autowired
	private LeadingUserMapper leadingUserMapper;
	@Autowired 
	private TcodeMapper tcodeMapper;
	@Override
	public int updateByTel(LeadingUser tel) {
		
		return leadingUserMapper.updateByPrimaryKey(tel);
	}

	@Override
	public LeadingUser selectByTel(String tel) {
		// TODO Auto-generated method stub
		return leadingUserMapper.selectByTel(tel);
	}

	@Override
	public LeadingUser selectByMail(String mail) {
		// TODO Auto-generated method stub
		return leadingUserMapper.selectByMail(mail);
	}

	@Override
	public int insertCode(Tcode record) {
		// TODO Auto-generated method stub
		return tcodeMapper.insertSelective(record) ;
	}

	

	@Override
	public Tcode selectByCode(Tcode  record) {
		// TODO Auto-generated method stub
		return tcodeMapper.selectByCode(record);
	}

}
