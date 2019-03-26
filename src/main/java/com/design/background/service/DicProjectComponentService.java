package com.design.background.service;

import com.design.background.entity.DicProjectComponent;

import java.util.List;

public interface DicProjectComponentService {
	
	List<DicProjectComponent> selectProjectComponent();
	
	List<DicProjectComponent> selectProjectComponent(String description);

	boolean insertProjectComponent(int code, String description);

	boolean deleteById(int id);

	boolean insertProjectComponentSelective(DicProjectComponent projectComponent);

	DicProjectComponent selectByPrimaryKey(Long id);

	DicProjectComponent selectProjectComponentByDescription(String description, int code);

	/**
	 * 由code值获取字典对象
	 * @author 孟晓冬
	 * @param code
	 * @return
	 */
	DicProjectComponent selectByCode(Integer code);
}
