package com.design.background.mapper;

import java.util.List;

import com.design.background.entity.DicDefaultRate;
import com.design.background.entity.ExampleProject;

public interface DicDefaultRateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DicDefaultRate record);

    int insertSelective(DicDefaultRate record);

    DicDefaultRate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicDefaultRate record);

    int updateByPrimaryKey(DicDefaultRate record);
}