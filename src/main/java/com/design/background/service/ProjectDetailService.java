package com.design.background.service;

import com.design.background.entity.ProjectDetail;

/**
 * @ClassName: ProjectDetailService
 * @Description: TODO
 * @Author: HRX
 * @Date: 2019/3/5 17:44
 **/
public interface ProjectDetailService {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectDetail record);

    int insertSelective(ProjectDetail record);

    ProjectDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectDetail record);

    int updateByPrimaryKey(ProjectDetail record);
}
