package com.design.background.mapper;

import com.design.background.entity.RateManagement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RateManagementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RateManagement record);

    int insertSelective(RateManagement record);

    RateManagement selectByPrimaryKey(Long id);

    List<RateManagement> selectAll(@Param("mycode" ) Integer code);

    int updateByPrimaryKeySelective(RateManagement record);

    int updateByPrimaryKey(RateManagement record);
}