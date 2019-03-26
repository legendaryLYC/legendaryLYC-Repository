package com.design.background.service.impl;

import com.design.background.entity.DicProjectStage;
import com.design.background.mapper.DicProjectStageMapper;
import com.design.background.service.DicProjectStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectStageService")
public class DicProjectStageServiceImpl implements DicProjectStageService{

	@Autowired
	DicProjectStageMapper projectStageMapper;
	
	@Override
	public List<DicProjectStage> selectProjectStage() {
		List<DicProjectStage> projectStageList = null;
		try {
			projectStageList = projectStageMapper.selectAllProjectStage();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return projectStageList;
	}

	@Override
	public List<DicProjectStage> selectProjectStage(String description) {
		List<DicProjectStage> projectStageList = null;
		try {
			projectStageList = projectStageMapper.selectProjectStage(description);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return projectStageList;
	}

	@Override
	public boolean deleteById(int id) {
		boolean flag=false;
		try {
		flag = projectStageMapper.deleteById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertProjectStage(int code, String name) {
		boolean flag = false;
		try {
		flag = projectStageMapper.insertProjectStage(code, name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertProjectStageSelective(DicProjectStage projectStage) {
		boolean flag=false;
		try {
		flag = projectStageMapper.insertProjectStageSelective(projectStage);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public DicProjectStage selectByPrimaryKey(Long id) {
		DicProjectStage projectStage = projectStageMapper.selectByPrimaryKey(id);
		return projectStage;
	}

	@Override
	public DicProjectStage selectProjectStageByDescription(String description,int code) {
		DicProjectStage ProjectStage = projectStageMapper.selectProjectStageByDescription(code,description);
		return ProjectStage;
	}
}
