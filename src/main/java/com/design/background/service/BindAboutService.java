package com.design.background.service;

import java.util.List;

import com.design.background.entity.DicColumn;

public interface BindAboutService {
	List<DicColumn> selectAll(String description);
	
	int deleteByPrimaryKey(Long id);
	
	int insert(DicColumn record);
	
	int insertSelective(DicColumn record);

    DicColumn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicColumn record);

    int updateByPrimaryKeyWithBLOBs(DicColumn record);

    int updateByPrimaryKey(DicColumn record);
}
