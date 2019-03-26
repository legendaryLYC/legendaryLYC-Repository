package com.design.background.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.design.background.entity.*;
import com.design.background.mapper.DesigntypeExtendMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.background.form.ProjectForm;
import com.design.background.form.ProjectPayForm;
import com.design.background.form.ProjectorForm;
import com.design.background.mapper.LeadingUserMapper;
import com.design.background.model.AreaModel;
import com.design.background.service.DicService;
import com.design.background.service.LeadingUserProjectorService;

/**项目方管理实现类
 * @author 任松
 * 
 */
@Service("LeadingUserProjectorService")
public class LeadingUserProjectorServiceImpl implements LeadingUserProjectorService {
	private static final Logger logger = LoggerFactory.getLogger(LeadingUserProjectorServiceImpl.class);


	@Autowired
	private LeadingUserMapper leadingUserMapper;

	@Autowired
	private DesigntypeExtendMapper designtypeExtendMapper;
	/**
	 * 根据条件查询项目方列表
	 * @param projectDetail
	 * @return
	 */
	
	@Override
	public List<ProjectorForm> selectProjectorsSelective(LeadingUser leadingUser) {
		//存查询结果
		List<ProjectorForm> projectors = null;
		//去除空串,防止""查库报错
		leadingUser.setUsername("".equals(leadingUser.getUsername()) ? null : leadingUser.getUsername());
		try {
			projectors = leadingUserMapper.selectProjectorsSelective(leadingUser);
		} catch (Exception e) {
			logger.error("条件查询项目方失败!"+e.getMessage());
		}
		return projectors;
	}
	
	
	
	/**
	 * 根据名称搜索项目方
	 * @param name
	 * @return
	 */

	@Override
	public List<LeadingUser> select(String name) {
		// TODO Auto-generated method stub
		logger.info("======== 进入select方法,参数名name : "+name+" =========");
		List<LeadingUser> leadingUsers = new ArrayList<>();
		leadingUsers=leadingUserMapper.select(name);
		try {
			logger.info("======== 查询到的项目方信息 : " + leadingUsers.toString() + " ==========");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return leadingUsers;
	}
	
	@Override
	public List<LeadingUser>selectByName(String name){
		logger.info("======== 进入selectByName方法，参数name:"+name+" ========");
		List<LeadingUser> leadingUsers=new ArrayList<>();
		leadingUsers=leadingUserMapper.selectByName(name);
		try {
			logger.info("======== 查询到的项目方信息："+leadingUsers.toString()+" ========");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return leadingUsers;
	};
	
	/**
	 * 根据项目方id更新项目方信息
	 * @param leadingUser
	 * @return
	 */
	@Override
	public int updateById(LeadingUser leadingUser) {
		logger.info("======== 进入updataById方法，参数userId:"+leadingUser+" ========");
		int key=leadingUserMapper.updateByPrimaryKeySelective(leadingUser);
		logger.info("======== 更新结果: "+key+" ========");
		return key;
	};
	
	/**
	 * 根据项目方id删除项目方信息
	 * @param id
	 * @return
	 */
	@Override
	public int deleteById(Long id) {
		logger.info("======== 进入deleteById方法，参数userId:"+id+" ========");
		int key=leadingUserMapper.deleteByPrimaryKey(id);
		logger.info("======== 删除结果:"+key+" ========");
		return key;
	}
	
	/**
	 * 增加项目方信息
	 * @param leadingUser
	 * @return
	 */
	@Override
	public int insert(LeadingUser leadingUser) {
		logger.info("======== 进入insert方法，参数名leadingUser：:"+leadingUser+" ========");
		int key=leadingUserMapper.insert(leadingUser);
		logger.info("======== 插入结果:"+key+" ========");
		return 0;
	};
	
	/**
	 * 根据项目方id查询项目方信息
	 * @param id
	 * @return
	 */
	@Override
	public LeadingUser selectById(Long id) {
	
		return leadingUserMapper.selectByPrimaryKey(id);
		
	};
	
	
	
	/**
	 *查询最后一条物料信息的编号
	 *@param name
	 * @return
	 */
	@Override
	public Long selectLast() {
		// TODO Auto-generated method stub
		LeadingUser leadingUser = leadingUserMapper.selectLast();
		logger.info("MaterielImpl下的selectLast方法查出的materiel : " + leadingUser);
		Long id=(long) 0;
		id = leadingUser == null ? 1000:leadingUser.getId(); 
		logger.info("返回id为 : " +id);
		return id;
	}

	


	@Override
	public ProjectorForm selectUserAreaById(Long id, int areaId) {
		
		
		return leadingUserMapper.selectUserAreaById(id, areaId);
	}
	
	/**
	 * 任松
	 * 根据项目id获取设计师相关信息（列表）
	 */
	  @Override
	    public List<ProjectPayForm> selectprojectdetails(Long id) {
	         
	        return leadingUserMapper.selectprojectdetails(id);
	    }


	@Override
	public Integer[] selectDesignerMajorById(Long id) {
		DesigntypeExtend designtypeExtend = designtypeExtendMapper.selectByUserid(id);
		String type = designtypeExtend.getCode();
		Integer[] result = new Integer[5];
		if(type == null)
		{
			return null;
		}
		String[] arr = type.split(",");
		for(int i = 0;i<arr.length;i++)
		{
			result[i] = Integer.parseInt(arr[i]);
		}
		return result;
	}



	@Override
	public int selectByTel(String tel) {
		// TODO Auto-generated method stub
		LeadingUser key=leadingUserMapper.selectByTel(tel);
		if(key==null) {
			return 1;
		}
		return 0;
	}



	@Override
	public AreaModel selectAreaModelByUserId(Long id) {
		AreaModel result = null;
		result = leadingUserMapper.selectAreaModelByUserId(id);
		return result;
	}
}
