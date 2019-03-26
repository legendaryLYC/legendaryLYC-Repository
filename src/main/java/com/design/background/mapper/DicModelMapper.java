package com.design.background.mapper;

import java.util.List;

import com.design.background.entity.DicProjectScale;

public interface DicModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DicProjectScale record);

    int insertSelective(DicProjectScale record);

    DicProjectScale selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicProjectScale record);

    int updateByPrimaryKey(DicProjectScale record);
    
    //获取该字典的全部内容
    List<DicProjectScale> selectAll();
}