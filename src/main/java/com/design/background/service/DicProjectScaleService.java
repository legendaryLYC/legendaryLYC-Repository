package com.design.background.service;

import com.design.background.entity.DicProjectScale;

import java.util.List;

public interface DicProjectScaleService {

    List<DicProjectScale> getModels();

    List<DicProjectScale> selectProjectScale();
	
	List<DicProjectScale> selectProjectScale(String description);

	boolean insertProjectScale(int code, String description);

	boolean deleteById(int id);

	boolean insertProjectScaleSelective(DicProjectScale projectScale);

	DicProjectScale selectByPrimaryKey(Long id);

	DicProjectScale selectProjectScaleByDescription(String description, int code);
	
	
}
