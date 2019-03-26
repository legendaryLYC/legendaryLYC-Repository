package com.design.background.service;

import com.design.background.entity.DicProjectFee;

import java.util.List;

public interface DicProjectFeeService {
	
	List<DicProjectFee> selectProjectFee();
	
	List<DicProjectFee> selectProjectFee(String description);

	boolean insertProjectFee(int code, String description);

	boolean deleteById(int id);

	boolean insertProjectFeeSelective(DicProjectFee projectFee);

	DicProjectFee selectByPrimaryKey(Long id);

	DicProjectFee selectProjectFeeByDescription(String description, int code);
	
	
}
