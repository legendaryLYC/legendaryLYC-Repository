package com.design.background.service.impl;

import com.design.background.entity.DicProjectFee;
import com.design.background.mapper.DicProjectFeeMapper;
import com.design.background.service.DicProjectFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectFeeService")
public class DicProjectFeeServiceImpl implements DicProjectFeeService{

	@Autowired
	DicProjectFeeMapper projectFeeMapper;
	
	@Override
	public List<DicProjectFee> selectProjectFee() {
		List<DicProjectFee> projectFeeList = null;
		try {
			projectFeeList = projectFeeMapper.selectAllProjectFee();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return projectFeeList;
	}

	@Override
	public List<DicProjectFee> selectProjectFee(String description) {
		List<DicProjectFee> projectFeeList = null;
		try {
			projectFeeList = projectFeeMapper.selectProjectFee(description);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return projectFeeList;
	}

	@Override
	public boolean deleteById(int id) {
		boolean flag=false;
		try {
		flag = projectFeeMapper.deleteById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertProjectFee(int code, String name) {
		boolean flag = false;
		try {
		flag = projectFeeMapper.insertProjectFee(code, name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertProjectFeeSelective(DicProjectFee projectFee) {
		boolean flag=false;
		try {
		flag = projectFeeMapper.insertProjectFeeSelective(projectFee);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public DicProjectFee selectByPrimaryKey(Long id) {
		DicProjectFee projectFee = projectFeeMapper.selectByPrimaryKey(id);
		return projectFee;
	}

	@Override
	public DicProjectFee selectProjectFeeByDescription(String description,int code) {
		DicProjectFee ProjectFee = projectFeeMapper.selectProjectFeeByDescription(code,description);
		return ProjectFee;
	}
}
