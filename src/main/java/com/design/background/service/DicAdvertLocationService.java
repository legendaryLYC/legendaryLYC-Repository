package com.design.background.service;

import java.util.List;

import com.design.background.entity.DicAdvertisingLocation;

public interface DicAdvertLocationService {

	List<DicAdvertisingLocation> showAllAdvertLocation(DicAdvertisingLocation advert);

	int deleteAdvertLocation(Long id);

	int allAdvertLocationAdd(DicAdvertisingLocation advert);

	DicAdvertisingLocation selectById(Long id);

	int updateAdvertLocation(DicAdvertisingLocation advert);

}
