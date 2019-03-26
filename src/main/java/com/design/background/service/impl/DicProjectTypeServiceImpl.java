package com.design.background.service.impl;

import com.design.background.entity.DicDefaultRate;
import com.design.background.entity.DicProjectType;
import com.design.background.mapper.DicDefaultRateMapper;
import com.design.background.mapper.DicProjectTypeMapper;
import com.design.background.service.DicProjectTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectTypeService")
public class DicProjectTypeServiceImpl implements DicProjectTypeService{

	@Autowired
	private DicProjectTypeMapper projectTypeMapper;
	
	@Override
	public List<DicProjectType> selectProjectType() {
		List<DicProjectType> projectTypeList = null;
		try {
			projectTypeList = projectTypeMapper.selectAllProjectType();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return projectTypeList;
	}

	@Override
	public List<DicProjectType> selectProjectType(String description) {
		List<DicProjectType> projectTypeList = null;
		try {
			projectTypeList = projectTypeMapper.selectProjectType(description);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return projectTypeList;
	}

	@Override
	public boolean deleteById(int id) {
		boolean flag=false;
		try {
		flag = projectTypeMapper.deleteById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertProjectType(int code, String name) {
		boolean flag = false;
		try {
		flag = projectTypeMapper.insertProjectType(code, name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertProjectTypeSelective(DicProjectType projectType) {
		boolean flag=false;
		try {
		flag = projectTypeMapper.insertProjectTypeSelective(projectType);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public DicProjectType selectByPrimaryKey(Long id) {
		DicProjectType projectType = projectTypeMapper.selectByPrimaryKey(id);
		return projectType;
	}

	@Override
	public DicProjectType selectProjectTypeByDescription(String description,int code) {
		DicProjectType ProjectType = projectTypeMapper.selectProjectTypeByDescription(code,description);
		return ProjectType;
	}

	@Override
	public String selectByCode(Integer code) {
		return projectTypeMapper.selectByCode(code).getDescription();
	}

}
