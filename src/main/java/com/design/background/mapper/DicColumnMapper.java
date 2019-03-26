package com.design.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.design.background.entity.DicColumn;

public interface DicColumnMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DicColumn record);

    int insertSelective(DicColumn record);

    DicColumn selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicColumn record);

    int updateByPrimaryKeyWithBLOBs(DicColumn record);

    int updateByPrimaryKey(DicColumn record);
    
    List<DicColumn> selectAll(@Param("description")String description);
}