package com.design.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.entity.CompanyManagement;

import com.design.background.mapper.CompanyManagementMapper;
import com.design.background.service.CompanyManagementService;

@Service
public class CompanyManagementServiceImpl implements CompanyManagementService {

	
	@Autowired
	private CompanyManagementMapper companyManagementMapper;
	
	/**
	 * 
	* @Author 任松 
	* @Title: selectAll  
	* @Description: TODO 查询所有的文章
	* @param @param description
	* @param @return      
	* @Date 2019年2月16日 下午2:58:26
	* @throws
	 */
	@Override
	public List<CompanyManagement> selectAll(String companyName) {
		// TODO Auto-generated method stub
		return companyManagementMapper.selectAll(companyName);
	}
	
	/**
	 * 
	* @Author 任松 
	* @Title: deleteByPrimaryKey  
	* @Description: TODO 通过id删除文章
	* @param @param id
	* @param @return      
	* @Date 2019年2月16日 下午2:59:18
	* @throws
	 */
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return companyManagementMapper.deleteByPrimaryKey(id);
	}
	
	/**
	 * 
	* @Author 任松 
	* @Title: insert  
	* @Description: TODO 插入文章
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午2:59:31
	* @throws
	 */
	@Override
	public int insert(CompanyManagement record) {
		// TODO Auto-generated method stub
		return companyManagementMapper.insertSelective(record);
	}
	
	/**
	 * 
	* @Author 任松 
	* @Title: insertSelective  
	* @Description: TODO 选择性插入客户
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午2:59:45
	* @throws
	 */
	@Override
	public int insertSelective(CompanyManagement record) {
		// TODO Auto-generated method stub
		return companyManagementMapper.insertSelective(record);
	}
	
	/**
	 * 
	* @Author 任松 
	* @Title: selectByPrimaryKey  
	* @Description: TODO 通过主键查询
	* @param @param id
	* @param @return      
	* @Date 2019年2月16日 下午3:00:12
	* @throws
	 */
	@Override
	public CompanyManagement selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return companyManagementMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 
	* @Author 任松 
	* @Title: updateByPrimaryKeySelective  
	* @Description: TODO 通过id选择性更改除内容以外的字段
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午3:00:39
	* @throws
	 */
	@Override
	public int updateByPrimaryKeySelective(CompanyManagement record) {
		// TODO Auto-generated method stub
		return companyManagementMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 
	* @Author 任松 
	* @Title: updateByPrimaryKeyWithBLOBs  
	* @Description: TODO 通过id选择性更改所有字段
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午3:01:18
	* @throws
	 */
	
	@Override
	public int updateByPrimaryKeyWithBLOBs(CompanyManagement record) {
		// TODO Auto-generated method stub
		return companyManagementMapper.updateByPrimaryKeyWithBLOBs(record);
	}
	
	/**
	 * 
	* @Author 任松 
	* @Title: updateByPrimaryKey  
	* @Description: TODO 通过主键更新除内容以外的其他字段
	* @param @param record
	* @param @return      
	* @Date 2019年2月16日 下午3:01:54
	* @throws
	 */
	@Override
	public int updateByPrimaryKey(CompanyManagement record) {
		// TODO Auto-generated method stub
		return companyManagementMapper.updateByPrimaryKey(record);
	}
	/**任松
	 * 获取所有的客户
	 */
	@Override
	public List<CompanyManagement> selectAll() {
		// TODO Auto-generated method stub
		return companyManagementMapper.selectAllCompany();
	}
	
	
	
}
