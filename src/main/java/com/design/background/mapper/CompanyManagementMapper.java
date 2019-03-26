package com.design.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.design.background.entity.CompanyManagement;


public interface CompanyManagementMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyManagement record);

    int insertSelective(CompanyManagement record);

    CompanyManagement selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyManagement record);

    int updateByPrimaryKey(CompanyManagement record);
    
    int updateByPrimaryKeyWithBLOBs(CompanyManagement record);
    
    List<CompanyManagement> selectAll(@Param("companyName")String companyName);
    
    List<CompanyManagement> selectAllCompany();
}
/**
 *  

   

 

   
  

    int updateByPrimaryKeyWithBLOBs(DicColumn record);

    int updateByPrimaryKey(DicColumn record);
    
    List<DicColumn> selectAll(@Param("description")String description);
 * 
 * 
 * 
 */


