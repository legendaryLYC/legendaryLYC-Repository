package com.design.background.service.impl;

import com.design.background.entity.DicProjectPeriod;
import com.design.background.mapper.DicProjectPeriodMapper;
import com.design.background.service.DicProjectPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectPeriodService")
public class DicProjectPeriodServiceImpl implements DicProjectPeriodService{

	@Autowired
	DicProjectPeriodMapper projectPeriodMapper;
	
	@Override
	public List<DicProjectPeriod> selectProjectPeriod() {
		List<DicProjectPeriod> projectPeriodList = null;
		try {
			projectPeriodList = projectPeriodMapper.selectAllProjectPeriod();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return projectPeriodList;
	}

	@Override
	public List<DicProjectPeriod> selectProjectPeriod(String description) {
		List<DicProjectPeriod> projectPeriodList = null;
		try {
			projectPeriodList = projectPeriodMapper.selectProjectPeriod(description);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return projectPeriodList;
	}

	@Override
	public boolean deleteById(int id) {
		boolean flag=false;
		try {
		flag = projectPeriodMapper.deleteById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertProjectPeriod(int code, String name) {
		boolean flag = false;
		try {
		flag = projectPeriodMapper.insertProjectPeriod(code, name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertProjectPeriodSelective(DicProjectPeriod projectPeriod) {
		boolean flag=false;
		try {
		flag = projectPeriodMapper.insertProjectPeriodSelective(projectPeriod);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public DicProjectPeriod selectByPrimaryKey(Long id) {
		DicProjectPeriod projectPeriod = projectPeriodMapper.selectByPrimaryKey(id);
		return projectPeriod;
	}

	@Override
	public DicProjectPeriod selectProjectPeriodByDescription(String description,int code) {
		DicProjectPeriod ProjectPeriod = projectPeriodMapper.selectProjectPeriodByDescription(code,description);
		return ProjectPeriod;
	}
}
