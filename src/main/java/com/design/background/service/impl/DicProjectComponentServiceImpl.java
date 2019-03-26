package com.design.background.service.impl;

import com.design.background.entity.DicProjectComponent;
import com.design.background.entity.ProjectDetail;
import com.design.background.mapper.DicProjectComponentMapper;
import com.design.background.service.DicProjectComponentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectComponentService")
public class DicProjectComponentServiceImpl implements DicProjectComponentService{

	@Autowired
	DicProjectComponentMapper projectComponentMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(DicProjectComponentServiceImpl.class);
	
	@Override
	public List<DicProjectComponent> selectProjectComponent() {
		List<DicProjectComponent> projectComponentList = null;
		try {
			projectComponentList = projectComponentMapper.selectAllProjectComponent();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return projectComponentList;
	}

	@Override
	public List<DicProjectComponent> selectProjectComponent(String description) {
		List<DicProjectComponent> projectComponentList = null;
		try {
			projectComponentList = projectComponentMapper.selectProjectComponent(description);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return projectComponentList;
	}

	@Override
	public boolean deleteById(int id) {
		boolean flag=false;
		try {
		flag = projectComponentMapper.deleteById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertProjectComponent(int code, String name) {
		boolean flag = false;
		try {
		flag = projectComponentMapper.insertProjectComponent(code, name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertProjectComponentSelective(DicProjectComponent projectComponent) {
		boolean flag=false;
		try {
		flag = projectComponentMapper.insertProjectComponentSelective(projectComponent);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public DicProjectComponent selectByPrimaryKey(Long id) {
		DicProjectComponent projectComponent = projectComponentMapper.selectByPrimaryKey(id);
		return projectComponent;
	}

	@Override
	public DicProjectComponent selectProjectComponentByDescription(String description,int code) {
		DicProjectComponent ProjectComponent = projectComponentMapper.selectProjectComponentByDescription(code,description);
		return ProjectComponent;
	}

	@Override
	public DicProjectComponent selectByCode(Integer code) {
		DicProjectComponent result = null;
		try {
			result = projectComponentMapper.selectByCode(code);
		} catch (Exception e) {
			logger.error("根据code查询设计师类型字典表失败!"+e.getMessage());
		}
		return result;
	}
}
