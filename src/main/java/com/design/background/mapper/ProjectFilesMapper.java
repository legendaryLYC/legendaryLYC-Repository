package com.design.background.mapper;

import java.util.List;

import com.design.background.entity.ProjectFiles;

public interface ProjectFilesMapper {
    int deleteByPrimaryKey(Long id);
    int deleteByIds(Long[] ids);

    int insert(ProjectFiles record);

    int insertSelective(ProjectFiles record);

    ProjectFiles selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectFiles record);

    int updateByPrimaryKey(ProjectFiles record);
    
    /**
     * 根据项目id查询相应文件
     * @author 孟晓冬
     */
    List<ProjectFiles> selectByProjectId(Long id);
}