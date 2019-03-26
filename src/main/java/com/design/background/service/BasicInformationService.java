package com.design.background.service;

import java.util.List;

import com.design.background.entity.BasicInformation;

/**
 * @Author： 宋博通
 * @Date： 2019/2/26
 * @Description： 基础信息修改的service
 */
public interface BasicInformationService {

    int deleteInformation(Long id);

    int insertInformationSelective(BasicInformation record);

    BasicInformation selectByid(Long id);

    int updateInformationSelective(BasicInformation record);

    List<BasicInformation> selectInformationList();
    
    List<BasicInformation> selectByname(String title);

    /**
     * 应用一条基础信息项
     * @Author: 孟晓冬
     * @param id
     * @return
     */
    int applySetting(Long id);

    /**
     * 查询应用中的一条基础信息设置
     * @Author: 孟晓冬
     * @param id
     * @return
     */
    BasicInformation getSelectedSetting();
}
