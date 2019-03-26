package com.design.background.mapper;
import com.design.background.entity.DicCity;
import com.design.background.entity.DicProvince;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;


public interface DicCityMapper {
	
	List<DicCity> selectAllCity();

    DicCity selectCityById(String id);

    List<DicCity> selectCityWithProvince(String id);
}
