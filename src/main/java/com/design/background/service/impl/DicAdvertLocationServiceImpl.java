package com.design.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.entity.DicAdvertisingLocation;
import com.design.background.mapper.DicAdvertisingLocationMapper;
import com.design.background.service.DicAdvertLocationService;

@Service("DicAdvertLocationService")
public class DicAdvertLocationServiceImpl implements DicAdvertLocationService {
	
	@Autowired
	DicAdvertisingLocationMapper dicAdvertisingLocationMapper;
 
	@Override
	public List<DicAdvertisingLocation> showAllAdvertLocation(DicAdvertisingLocation advert) {
		List<DicAdvertisingLocation> AdvertLocationList = dicAdvertisingLocationMapper.selectAll(advert);
		return AdvertLocationList;
	}



	@Override
	public int deleteAdvertLocation(Long id) {
		int flag =  dicAdvertisingLocationMapper.deleteByPrimaryKey(id);
		return flag;
	}


	@Override
	public int allAdvertLocationAdd(DicAdvertisingLocation advert) {
		int flag = dicAdvertisingLocationMapper.insertSelective(advert);
		return flag;
	}

	@Override
	public DicAdvertisingLocation selectById(Long id) {
		DicAdvertisingLocation AdvertLocation = dicAdvertisingLocationMapper.selectByPrimaryKey(id);
		return AdvertLocation;
	}
	@Override
	public int updateAdvertLocation(DicAdvertisingLocation advert) {
		int flag = dicAdvertisingLocationMapper.updateByPrimaryKeySelective(advert);
		return flag;
	}

}
