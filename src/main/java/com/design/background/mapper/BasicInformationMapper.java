package com.design.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.design.background.entity.BasicInformation;

public interface BasicInformationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BasicInformation record);

    int insertSelective(BasicInformation record);

    BasicInformation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BasicInformation record);

    int updateByPrimaryKey(BasicInformation record);

    List<BasicInformation> selectInformationlist();
    
    List<BasicInformation> selectInformationByname(@Param("titlename")String titlename);

    /**
     * 把所有设置项的被选中字段清零
     * @Author:孟晓冬
     * @return
     */
    int cleanSelected();

    /**
     * 查询应用中的一条基础信息设置
     * @Author: 孟晓冬
     * @return
     */
    BasicInformation selectSelectedSetting();
}