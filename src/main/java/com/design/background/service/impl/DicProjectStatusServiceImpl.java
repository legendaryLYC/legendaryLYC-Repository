package com.design.background.service.impl;

import com.design.background.entity.DicProjectStatus;
import com.design.background.mapper.DicProjectStatusMapper;
import com.design.background.service.DicProjectStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectStatusService")
public class DicProjectStatusServiceImpl implements DicProjectStatusService{

	@Autowired
	DicProjectStatusMapper projectStatusMapper;
	
	@Override
	public List<DicProjectStatus> selectProjectStatus() {
		List<DicProjectStatus> projectStatusList = null;
		try {
			projectStatusList = projectStatusMapper.selectAllProjectStatus();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return projectStatusList;
	}

	@Override
	public List<DicProjectStatus> selectProjectStatus(String description) {
		List<DicProjectStatus> projectStatusList = null;
		try {
			projectStatusList = projectStatusMapper.selectProjectStatus(description);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return projectStatusList;
	}
	
	
	/**
	 * @Author HRX
	 * @Description //TODO 通过code查询阶段
	 * @Date 2019/3/1 17:01
	 * @Param 
	 * @return 
	 **/
	@Override
	public String selectByCode(Integer code) {
		return projectStatusMapper.selectByCode(code);
	}

	@Override
	public boolean deleteById(int id) {
		boolean flag=false;
		try {
		flag = projectStatusMapper.deleteById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertProjectStatus(int code, String name) {
		boolean flag = false;
		try {
		flag = projectStatusMapper.insertProjectStatus(code, name);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertProjectStatusSelective(DicProjectStatus projectStatus) {
		boolean flag=false;
		try {
		flag = projectStatusMapper.insertProjectStatusSelective(projectStatus);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public DicProjectStatus selectByPrimaryKey(Long id) {
		DicProjectStatus projectStatus = projectStatusMapper.selectByPrimaryKey(id);
		return projectStatus;
	}

	@Override
	public DicProjectStatus selectProjectStatusByDescription(String description,int code) {
		DicProjectStatus ProjectStatus = projectStatusMapper.selectProjectStatusByDescription(code,description);
		return ProjectStatus;
	}
}
