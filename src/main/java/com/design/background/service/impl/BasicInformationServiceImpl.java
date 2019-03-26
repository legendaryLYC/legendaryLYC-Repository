package com.design.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.entity.BasicInformation;
import com.design.background.mapper.BasicInformationMapper;
import com.design.background.mapper.ProjectDetailMapper;
import com.design.background.service.BasicInformationService;

/**
 * @author 宋博通
 *基础信息编辑的方法
 */
@Service
public class BasicInformationServiceImpl implements BasicInformationService{

	@Autowired
	private BasicInformationMapper basicInformationMapper;

	@Override
	public int deleteInformation(Long id) {
		// TODO Auto-generated method stub
		return basicInformationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertInformationSelective(BasicInformation record) {
		// TODO Auto-generated method stub
		return basicInformationMapper.insertSelective(record);
	}

	@Override
	public BasicInformation selectByid(Long id) {
		// TODO Auto-generated method stub
		return basicInformationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateInformationSelective(BasicInformation record) {
		// TODO Auto-generated method stub
		return basicInformationMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<BasicInformation> selectInformationList() {
		// TODO Auto-generated method stub
		return basicInformationMapper.selectInformationlist();
	}

	@Override
	public List<BasicInformation> selectByname(String title) {
		// TODO Auto-generated method stub
		return basicInformationMapper.selectInformationByname(title);
	}

	@Override
	public int applySetting(Long id) {

		basicInformationMapper.cleanSelected();
		//把该id设置项选中更新到库里
		BasicInformation bas = new BasicInformation();
		bas.setId(id);
		bas.setIsSelected(1);
		return basicInformationMapper.updateByPrimaryKeySelective(bas);
	}

	@Override
	public BasicInformation getSelectedSetting() {
		return basicInformationMapper.selectSelectedSetting();
	}
}
