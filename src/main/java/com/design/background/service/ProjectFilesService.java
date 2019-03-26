package com.design.background.service;

import java.util.List;

import com.design.background.entity.ProjectFiles;

public interface ProjectFilesService {
	
	int insert(ProjectFiles record);

    int insertSelective(ProjectFiles record);
    
    /**
     * 根据项目id查询对应的文件集合
     * @author 孟晓冬
     */
    List<ProjectFiles> getFilesByProjectId(Long id);
    
    
    int deleteByPrimaryKey(Long id);
}
