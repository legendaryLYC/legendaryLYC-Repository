package com.design.background.mapper;

import java.util.List;

import com.design.background.entity.AdvertisingManagement;
import org.apache.ibatis.annotations.Param;

public interface AdvertisingManagementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdvertisingManagement record);

    int insertSelective(AdvertisingManagement record);

    AdvertisingManagement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdvertisingManagement record);

    int updateByPrimaryKey(AdvertisingManagement record);
    
    List<AdvertisingManagement> selectAll(AdvertisingManagement advertisingManagement);

    /**
     * @Author:孟晓冬
     * 查询所有位置被选中的广告
     * @return
     */
    List<AdvertisingManagement> selectSelectedLocations();

    /**
     * @Author:孟晓冬
     * 清空该位置所有选中信息
     * @return
     */
    int unselectedAdvertisings(@Param(value="locationCode")Integer locationCode);
    
}