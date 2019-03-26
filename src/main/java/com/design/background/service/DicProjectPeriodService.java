package com.design.background.service;

import com.design.background.entity.DicProjectPeriod;

import java.util.List;

public interface DicProjectPeriodService {

	List<DicProjectPeriod> selectProjectPeriod();

	List<DicProjectPeriod> selectProjectPeriod(String description);

	boolean insertProjectPeriod(int code, String description);

	boolean deleteById(int id);

	boolean insertProjectPeriodSelective(DicProjectPeriod projectPeriod);

	DicProjectPeriod selectByPrimaryKey(Long id);

	DicProjectPeriod selectProjectPeriodByDescription(String description, int code);
	
	
}
