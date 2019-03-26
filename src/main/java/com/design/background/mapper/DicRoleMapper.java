package com.design.background.mapper;

import java.util.List;

import com.design.background.entity.DicRole;

public interface DicRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DicRole record);

    int insertSelective(DicRole record);

    DicRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicRole record);

    int updateByPrimaryKey(DicRole record);

	List<DicRole> selectAllRoles();
}