package com.design.background.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.design.background.entity.LeadingUser;
import com.design.background.service.LeadingUserProjectorService;
import com.design.background.service.MessageManageService;
import com.design.background.service.ProjectManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.entity.DesignerRelationship;
import com.design.background.entity.ProjectDetail;
import com.design.background.form.DesignerRelationshipForm;
import com.design.background.mapper.DesignerRelationshipMapper;
import com.design.background.mapper.ProjectDetailMapper;
import com.design.background.service.DesignerRelationshipService;

/**
 * 设计师投标记录相关服务
 * @author 孟晓冬
 */
@Service
public class DesignerRelationshipServiceImpl implements DesignerRelationshipService {
	
	private static final Logger logger = LoggerFactory.getLogger(DesignerRelationshipServiceImpl.class);
	
	@Autowired
	private ProjectDetailMapper projectDetailMapper;
	@Autowired
	private DesignerRelationshipMapper designerRelationshipMapper;
	@Autowired
	private ProjectManageService projectManageService; // 调用晓东接口，在前台筛选出状态为待审核的项目
	@Autowired
	private  LeadingUserProjectorService leadingUserProjectorService;
	@Autowired
	private MessageManageService messageManageService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return designerRelationshipMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DesignerRelationship record) {
        return designerRelationshipMapper.insert(record);
    }

    @Override
    public int insertSelective(DesignerRelationship record) {
        return designerRelationshipMapper.insertSelective(record);
    }

    @Override
    public DesignerRelationship selectByPrimaryKey(Long id) {
        return designerRelationshipMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DesignerRelationship record) {
        return designerRelationshipMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DesignerRelationship record) {
        return designerRelationshipMapper.updateByPrimaryKey(record);
    }

	@Override
	public int deleteByUserIdAndProjectId(Long userId, Long projectId) {
		return designerRelationshipMapper.deleteByUserIdAndProjectId(userId,projectId);
	}

	@Override
	public DesignerRelationship selectJoinStatus(Long userId, Long projectId) {
		return designerRelationshipMapper.selectJoinStatus(userId,projectId);
	}

//	@Override
//	public Integer[] getUnselectedDesigners(Long projectId) {
//		ProjectDetail project = null;
//		try {
//			project = projectDetailMapper.selectByPrimaryKey(projectId);
//		} catch (Exception e) {
//			logger.error("根据id查询项目失败!"+e.getMessage());
//		}
//		//获取该项目需要的设计师类型,转换成Integer集合
//		String need = project.getComponentCode();
//		List<Integer> types = new ArrayList<>(5);
//		if(need == null) {
//			return new Integer[0];
//		}else {
//			for(String typeString : need.split(",")) {
//				types.add(Integer.parseInt(typeString));
//			}
//		}
//		List<Integer> selected = null;
//		try {
//			selected = designerRelationshipMapper.selectTypesByProjectId(projectId);
//		} catch (Exception e) {
//			logger.error("根据项目id查询已选设计师失败!"+e.getMessage());
//		}
//		//取差集,减去已被选中的设计师
//		types.removeAll(selected);
//		if(types.size() == 0) {
//			return new Integer[0];
//		}else {
//			return types.toArray(new Integer[types.size()]);
//		}
//	}
	@Override
	public Integer[] getUnselectedDesigners(Long projectId) {
		ProjectDetail project = null;
		try {
			project = projectDetailMapper.selectByPrimaryKey(projectId);
		} catch (Exception e) {
			logger.error("根据id查询项目失败!"+e.getMessage());
		}
		//用于传入查询条件
		DesignerRelationship designerRecord = new DesignerRelationship();
		//用于存放count结果
		int result = 0;
		//获取该项目需要的设计师类型
		String need = project.getComponentCode();
		if(need == null) {
			return new Integer[0];
		}
		//用于存放结果数组
		List<Integer> resultArr = new ArrayList<>();
		
		for(String type : need.split(",")) {
			if("".equals(type)) {
				continue;
			}
			Integer typeCode = Integer.valueOf(type);
			designerRecord.setProjectId(projectId);
			designerRecord.setTypeCode(typeCode);
			designerRecord.setIsSelected(1);
			
			try {
				result = designerRelationshipMapper.isSelected(designerRecord);
			} catch (Exception e) {
				logger.error("查询设计师关系表失败!"+e.getMessage());
			}
			//如果count值为0则加入结果数组
			if(result == 0) {
				resultArr.add(typeCode);
			}
		}
		if(resultArr.size() == 0) {
			return new Integer[0];
		}else {
			return resultArr.toArray(new Integer[resultArr.size()]);
		}
	}

	@Override
	public boolean examineProject(Long projectId,String message){
		boolean flag = true;
		Long type = 1014L;
		if(message!=null){
			type = 1015L;
		}
		else {
			message = "";
			type = 1014L;
		}
		ProjectDetail detail=projectManageService.selectByPrimaryKey(projectId);
		String projectName = detail.getName();
		List<DesignerRelationship> designerRelationship = designerRelationshipMapper.selectedDesigner(projectId);
		for(int i = 0; i<designerRelationship.size();i++){
			// 调用消息接口
			LeadingUser user = leadingUserProjectorService.selectById(designerRelationship.get(i).getUserId());
			String tel = user.getTel();
			Map<String,String> para= new HashMap();
			para.put("${projectName}",projectName);
			para.put("${message}",message);
			boolean messageResult = messageManageService.allNoticeAdd(tel,type, para);
			// 调用接口结束
		}
		return flag;
	}
	@Override
	public int selectCountByProjectId(Long projectId) {
		return designerRelationshipMapper.selectCountByProjectId(projectId);
	}

	@Override
	public List<DesignerRelationshipForm> selectDesignersByProjectId(Long projectId) {
		List<DesignerRelationshipForm> designerRelationshipForms = null;
		try {
			designerRelationshipForms = designerRelationshipMapper.selectDesignersByProjectId(projectId);
		} catch (Exception e) {
			logger.info("根据项目id获取报名信息失败"+e.getMessage());
		}
		
		return designerRelationshipForms;
	}

	@Override
	public Integer[] getUnselectedDesignersByProjectIdAndComponent(
			String component, Long projectId) {
		//该项目需要的设计师类型,转换成Integer集合
		List<Integer> types = new ArrayList<>(5);
		if(component == null) {
			return new Integer[0];
		}else {
			for(String typeString : component.split(",")) {
				types.add(Integer.parseInt(typeString));
			}
		}
		List<Integer> selected = null;
		try {
			selected = designerRelationshipMapper.selectTypesByProjectId(projectId);
		} catch (Exception e) {
			logger.error("根据项目id查询已选设计师失败!"+e.getMessage());
		}
		//取差集,减去已被选中的设计师
		types.removeAll(selected);
		if(types.size() == 0) {
			return new Integer[0];
		}else {
			return types.toArray(new Integer[types.size()]);
		}
	}
}
