package com.design.background.mapper;
import com.design.background.entity.DicProvince;

import java.util.List;


public interface DicProvinceMapper {
	
	List<DicProvince> selectAllProvince();

    DicProvince selectProvinceById(String id);

}
