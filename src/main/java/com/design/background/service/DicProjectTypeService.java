package com.design.background.service;

import com.design.background.entity.DicDefaultRate;
import com.design.background.entity.DicProjectType;

import java.util.List;

public interface DicProjectTypeService {
	
	List<DicProjectType> selectProjectType();
	
	List<DicProjectType> selectProjectType(String description);

	boolean insertProjectType(int code, String description);

	boolean deleteById(int id);

	boolean insertProjectTypeSelective(DicProjectType projectType);

	DicProjectType selectByPrimaryKey(Long id);

	DicProjectType selectProjectTypeByDescription(String description, int code);

	String selectByCode(Integer code);
	
}
