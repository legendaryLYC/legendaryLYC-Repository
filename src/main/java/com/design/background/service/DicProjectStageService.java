package com.design.background.service;

import com.design.background.entity.DicProjectStage;

import java.util.List;

public interface DicProjectStageService {
	
	List<DicProjectStage> selectProjectStage();
	
	List<DicProjectStage> selectProjectStage(String description);

	boolean insertProjectStage(int code, String description);

	boolean deleteById(int id);

	boolean insertProjectStageSelective(DicProjectStage projectStage);

	DicProjectStage selectByPrimaryKey(Long id);

	DicProjectStage selectProjectStageByDescription(String description, int code);
	
	
}
