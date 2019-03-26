package com.design.background.service;

import java.util.List;

import com.design.background.entity.AdvertisingManagement;

/**
 * @date 2019/2/26
 * @author 周天
 * @Description 平台信息管理和发布的Service层
 *
 */
public interface AdvertManageService {
	
	/**
	 * @Description 显示现在库中所有的广告信息
	 * @date 2019/2/26
	 * @author 周天
	 * @param advertisingManagement
	 * @return List<AdvertisingManagement>
	 */
	public List<AdvertisingManagement> showAllAdvert(AdvertisingManagement advertisingManagement);
	
	/**
	 * @Description 更新库中某一广告，根据advertisingManagement参数中的id值为条件
	 * @date 2019/2/26
	 * @author 周天
	 * @param advertisingManagement
	 * @return int
	 */
	public int updateAdvert(AdvertisingManagement advertisingManagement);
	
	/**
	 * @Description 删除某一条广告，根据参数id
	 * @date 2019/2/26
	 * @author 周天
	 * @param id
	 * @return int
	 */
	public int delateAdvert(Long id);
	
	/**
	 * @Description 增加一条广告信息
	 * @date 2019/2/26
	 * @author 周天
	 * @param advertisingManagement
	 * @return int
	 */
	public int allAdvertAdd(AdvertisingManagement advertisingManagement);
	
	/**
	 * @Description 通过advertisingManagement中location_code找到，某一条广告的所有信息
	 * @date 2019/2/26
	 * @author 周天
	 * @param advertisingManagement
	 * @return AdvertisingManagement
	 */
	public AdvertisingManagement selcetByAdvert(AdvertisingManagement advertisingManagement);


	/**
	 * @Author:孟晓冬
	 * 查询所有位置被选中的广告
	 * @return
	 */
	List<AdvertisingManagement> getSelectedLocations();

	/**
	 * @Author:孟晓冬
	 * 选中广告的处理
	 * @return
	 */
	int selectAdvertising(Long id);
}
