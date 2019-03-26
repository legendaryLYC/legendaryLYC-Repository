package com.design.background.mapper;

import java.util.List;

import com.design.background.entity.DicDispatch;

public interface DicDispatchMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DicDispatch record);

    int insertSelective(DicDispatch record);

    DicDispatch selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicDispatch record);

    int updateByPrimaryKey(DicDispatch record);
    
    /**
     * 获取所有的分派方式
     * @param id
     * @return
     * @author 孟晓冬
     */
    List<DicDispatch> selectAll();
}