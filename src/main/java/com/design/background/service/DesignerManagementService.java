package com.design.background.service;

import java.util.List;

import com.design.background.entity.LeadingUser;
import com.design.background.form.DesignerForm;


/**
 * @Author： Song
 * @Date： 2019/2/13
 * @Description： This is a class
 */
public interface DesignerManagementService {
	
	//获取所有设计师
	List<LeadingUser> selectAll();
	
	//通过id查找
	LeadingUser selectByPrimaryKey(Long id);
	
	//通过id删除
	int deleteDesigner(Long id);

	/**
	* @Author: 李永成
	* @Date: 2019/2/18
	* @Description:  根据设计师的组建分类和设计师的手机号查询设计师的信息
	* @Param:  int component,int phone
	* @return:  list<DesignerForm> DesignerForm
	*/
	List<DesignerForm> selectDesignerByPhone(String phone,int typeCode);

	LeadingUser selectDesignerByNum (String phone);
}
