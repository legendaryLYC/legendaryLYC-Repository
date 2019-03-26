package com.design.background.service.impl;

import com.design.background.entity.ProjectDetail;
import com.design.background.mapper.ProjectDetailMapper;
import com.design.background.service.ProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ProjectDetailImpl
 * @Description: TODO
 * @Author: HRX
 * @Date: 2019/3/5 17:45
 **/
@Service("/projectDetailService")
public class ProjectDetailServiceImpl implements ProjectDetailService {

    @Autowired
    private ProjectDetailMapper projectDetailMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return projectDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ProjectDetail record) {
        return projectDetailMapper.insert(record);
    }

    @Override
    public int insertSelective(ProjectDetail record) {
        return projectDetailMapper.insertSelective(record);
    }

    @Override
    public ProjectDetail selectByPrimaryKey(Long id) {
        return projectDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ProjectDetail record) {
        return projectDetailMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKey(ProjectDetail record) {
        return projectDetailMapper.updateByPrimaryKey(record);
    }
}
