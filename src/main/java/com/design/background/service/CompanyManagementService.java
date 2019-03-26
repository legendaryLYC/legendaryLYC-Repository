package com.design.background.service;

import java.util.List;

import com.design.background.entity.CompanyManagement;

/**
 * 客户管理
 * @author 任松
 *
 */
public interface CompanyManagementService {

	List<CompanyManagement> selectAll(String companyName);
	
	/**
	 * 获取所有的客户
	 * @return
	 */
	List<CompanyManagement> selectAll();
	
	int deleteByPrimaryKey(Long id);
	
	int insert(CompanyManagement record);
	
	int insertSelective(CompanyManagement record);

	CompanyManagement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyManagement record);

    int updateByPrimaryKeyWithBLOBs(CompanyManagement record);

    int updateByPrimaryKey(CompanyManagement record);
}
