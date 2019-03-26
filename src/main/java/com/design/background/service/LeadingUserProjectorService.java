package com.design.background.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.design.background.entity.LeadingUser;
import com.design.background.entity.ProjectDetail;
import com.design.background.form.ProjectForm;
import com.design.background.form.ProjectPayForm;
import com.design.background.form.ProjectorForm;
import com.design.background.model.AreaModel;

/**项目方管理
 * @author 任松
 * 
 */

public interface LeadingUserProjectorService {
	
	/**
	 * 按条件查询项目方列表
	 * @param 
	 * @return
	 */
	List<ProjectorForm> selectProjectorsSelective(@Param(value="leadingUser")LeadingUser leadingUser);
	
	int selectByTel(String tel);
 
	/**
	 * 根据名称搜索项目方
	 * @param name
	 * @return
	 */
	List<LeadingUser>selectByName(String name);
	
	/**
	 * 根据项目方id更新项目方信息
	 * @param leadingUser
	 * @return
	 */
	int updateById(LeadingUser leadingUser);
	
	/**任松
	 * 根据项目方id删除项目方信息
	 * @param id
	 * @return
	 */
	int deleteById(Long  id);
	
	/**任松
	 * 增加项目方
	 * @param leadingUser
	 * @return
	 */
	int insert(LeadingUser leadingUser);
	
	/**任松
	 * 根据项目方id查询项目方信息
	 * @param id
	 * @return
	 */
	LeadingUser selectById(Long userId);
	
	/**任松
	 *查询项目方具体信息
	 *@param name
	 * @return
	 */
	List<LeadingUser> select(String name);

	/**任松
	 * 查询最后一条数据
	 * @param name
	 * @return
	 */
	Long selectLast();
	
	/**任松
	 * 获取城市id和省份id
	 * @param id
	 * @param areaId
	 * @return
	 */
	ProjectorForm selectUserAreaById(Long id,int areaId);
	
	/**
	 * 任松
	 * 根据项目id获取设计师（相关信息）列表
	 * @param id
	 * @return
	 */
	 List<ProjectPayForm> selectprojectdetails(Long id);

	/**
	 * @Description: 根据设计师ID查询此设计师的专业数组编号
	 * @Param:
	 * @return:
	 * @Author: 李紫林
	 * @Date: 2019/2/27
	 */
	 Integer[] selectDesignerMajorById(Long id);

	 /**
	  * 根据用户id获取地区信息
	  * @author 孟晓冬
	  * @param id
	  * @return
	  */
	 AreaModel selectAreaModelByUserId(Long id);
}
