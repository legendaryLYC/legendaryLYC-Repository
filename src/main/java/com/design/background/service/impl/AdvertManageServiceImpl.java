package com.design.background.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.design.background.entity.AdvertisingManagement;
import com.design.background.mapper.AdvertisingManagementMapper;
import com.design.background.service.AdvertManageService;

@Service("AdvertManageService")
public class AdvertManageServiceImpl implements AdvertManageService{
	
	private static final Logger logger = LoggerFactory.getLogger(AdvertManageServiceImpl.class);
	
	@Autowired
	private AdvertisingManagementMapper advertisingManagementMapper;

	@Override
	public List<AdvertisingManagement> showAllAdvert(AdvertisingManagement advertisingManagement) {
		List<AdvertisingManagement> advertList = advertisingManagementMapper.selectAll(advertisingManagement);
		return advertList;
	}

	@Override
	public int updateAdvert(AdvertisingManagement advertisingManagement) {
		int flag = advertisingManagementMapper.updateByPrimaryKeySelective(advertisingManagement);
		return flag;
	}

	@Override
	public int delateAdvert(Long id) {
		int flag = advertisingManagementMapper.deleteByPrimaryKey(id);
		return flag;
	}

	@Override
	public AdvertisingManagement selcetByAdvert(AdvertisingManagement advertisingManagement) {
		List<AdvertisingManagement> adverList= advertisingManagementMapper.selectAll(advertisingManagement);
		if(null != adverList.get(0)) {
			return adverList.get(0);
		}
		return null;
	}

	@Override
	public int allAdvertAdd(AdvertisingManagement advertisingManagement) {
		int flag = advertisingManagementMapper.insertSelective(advertisingManagement);
		return flag;
	}

	@Override
	public List<AdvertisingManagement> getSelectedLocations() {
		List<AdvertisingManagement> list = advertisingManagementMapper.selectSelectedLocations();
		//按位置升序排列
		Collections.sort(list);
		return list;
	}

	@Override
	public int selectAdvertising(Long id) {
		//查询该id
		AdvertisingManagement old = advertisingManagementMapper.selectByPrimaryKey(id);
		//清除该位置所有广告选中信息
		advertisingManagementMapper.unselectedAdvertisings(old.getLocationCode());
		//选中该广告更新到库里
		old.setIsSelected(1);
		return advertisingManagementMapper.updateByPrimaryKeySelective(old);
	}
}
