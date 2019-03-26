package com.design.background.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.design.background.entity.ProjectFiles;
import com.design.background.mapper.ProjectFilesMapper;
import com.design.background.service.ProjectFilesService;

@Service("projectFilesService")
public class ProjectFilesServiceImpl implements ProjectFilesService{
	
	@Autowired
	private ProjectFilesMapper projectFilesMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectFilesServiceImpl.class);
	
	@Override
	public int insert(ProjectFiles record) {
		// TODO Auto-generated method stub
		return projectFilesMapper.insert(record);
	}

	@Override
	public int insertSelective(ProjectFiles record) {
		// TODO Auto-generated method stub
		return projectFilesMapper.insertSelective(record);
	}

	@Override
	public List<ProjectFiles> getFilesByProjectId(Long id) {
		List<ProjectFiles> files = null;
		try {
			files = projectFilesMapper.selectByProjectId(id);
		} catch (Exception e) {
			logger.error("根据项目id查询项目文件失败!"+e.getMessage());
		}
		return files;
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: deleteByPrimaryKey  
	* @Description: TODO 通过id删除文件路径记录
	* @param @param id
	* @param @return      
	* @Date 2019年2月21日 上午12:20:13
	* @throws
	 */
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return projectFilesMapper.deleteByPrimaryKey(id);
	}
	
}
