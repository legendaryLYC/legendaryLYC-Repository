package com.design.background.mapper;

import java.util.List;

import com.design.background.entity.DicAdvertisingLocation;

public interface DicAdvertisingLocationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DicAdvertisingLocation record);

    int insertSelective(DicAdvertisingLocation record);

    DicAdvertisingLocation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicAdvertisingLocation record);

    int updateByPrimaryKey(DicAdvertisingLocation record);
    
    List<DicAdvertisingLocation> selectAll(DicAdvertisingLocation record);
}