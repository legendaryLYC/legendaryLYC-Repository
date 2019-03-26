package com.design.background.service.impl;


import java.util.List;

import com.design.background.form.DesignerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.design.background.entity.LeadingUser;
import com.design.background.mapper.LeadingUserMapper;
import com.design.background.service.DesignerManagementService;

/**
 * @Author：宋博通
 * @Date： 2019/2/13
 * @Description： This is a class
 */
@Service("DesignerManagementService")
public class DesignerManagementServiceImpl implements DesignerManagementService {
    @Autowired
    private LeadingUserMapper leadingUserMapper;

	@Override
	public LeadingUser selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return leadingUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<LeadingUser> selectAll() {
		// TODO Auto-generated method stub
		return leadingUserMapper.selectAll();
	}

	@Override
	public int deleteDesigner(Long id) {
		// TODO Auto-generated method stub
		return leadingUserMapper.deleteByPrimaryKey(id);
	}
	@Override
	public 	List<DesignerForm>	selectDesignerByPhone(String phone,int typeCode){
		return leadingUserMapper.selectDesignerByPhone(phone,typeCode);
	}
	@Override
	public LeadingUser selectDesignerByNum(String phone){
		return leadingUserMapper.selectDesignerByNum(phone);
	}
}
