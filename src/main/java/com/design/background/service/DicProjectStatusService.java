package com.design.background.service;

import com.design.background.entity.DicProjectStatus;

import java.util.List;

public interface DicProjectStatusService {
	
	List<DicProjectStatus> selectProjectStatus();
	
	List<DicProjectStatus> selectProjectStatus(String description);

	String selectByCode(Integer code);

	boolean insertProjectStatus(int code, String description);

	boolean deleteById(int id);

	boolean insertProjectStatusSelective(DicProjectStatus projectStatus);

	DicProjectStatus selectByPrimaryKey(Long id);

	DicProjectStatus selectProjectStatusByDescription(String description, int code);
	
}
