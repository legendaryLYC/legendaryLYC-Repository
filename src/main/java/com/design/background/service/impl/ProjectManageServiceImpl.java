package com.design.background.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.design.background.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.design.background.entity.DesignerRelationship;
import com.design.background.entity.MoneyDistribution;
import com.design.background.entity.ProjectDetail;
import com.design.background.entity.ProjectFiles;
import com.design.background.form.BasicProjectForm;
import com.design.background.form.MoneyListForm;
import com.design.background.form.ProjectForm;
import com.design.background.model.ScreeningConditions;
import com.design.background.service.DesignerRelationshipService;
import com.design.background.service.DicProjectStatusService;
import com.design.background.service.ProjectFilesService;
import com.design.background.service.ProjectManageService;
import com.design.background.util.DateUtil;
import com.design.background.util.DesignFileUtils;

/**
 * @author 孟晓冬
 *项目管理相关方法
 */
@Service
public class ProjectManageServiceImpl implements ProjectManageService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectManageServiceImpl.class);

	@Autowired
	private ProjectDetailMapper projectDetailMapper;
	@Autowired
	private ProjectFilesService projectFilesService;
	@Autowired
	private ProjectFilesMapper projectFilesMapper;
	@Autowired
	private DesignerRelationshipMapper designerRelationshipMapper;
	@Autowired
	private DesignerRelationshipService designerRelationshipService;
	@Autowired
	private MoneyDistributionMapper moneyDistributionMapper;
	@Autowired
	private DicProjectStatusService dicProjectStatusService;
	
	@Override
	public List<ProjectForm> selectProjectsSelective(ProjectDetail projectDetail, Date from, Date to) {
		//存查询结果
		List<ProjectForm> projects = null;
		//去除空串,防止""查库报错
		projectDetail.setName("".equals(projectDetail.getName()) ? null : projectDetail.getName());
		try {
			projects = projectDetailMapper.selectProjectsSelective(projectDetail, from, to);
		} catch (Exception e) {
			logger.error("条件查询项目失败!"+e.getMessage());
		}
		return projects;
	}
	
	@Override
	public ProjectForm selectProjectsFormById(ProjectDetail projectDetail){
		ProjectForm project = null;
		try {
			project = projectDetailMapper.selectProjectFormById(projectDetail);
		} catch (Exception e) {
			logger.error("查询项目详情失败!"+e.getMessage());
			
		}
		if(project == null) {
			return null;
		}
		project.setProjectFiles(
				projectFilesService.getFilesByProjectId(project.getId()));
		return project;
	}
	
	@Override
	public Boolean deleteProjectById(Long id) {
		//记录删除结果
		int result = 0;
		ProjectDetail project = new ProjectDetail();
		project.setId(id);
		project.setIsDelete(1);
		try {
			result = projectDetailMapper.updateByPrimaryKeySelective(project);
		} catch (Exception e) {
			logger.error("按id删除项目失败!"+e.getMessage());
		}
		if(result == 1){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean updateProjectAll(ProjectDetail record) {
		//计算工期
		Integer constructionPeriod = 0;
		long longPeriod = (record.getDeadline().getTime()-record.getStartupDate().getTime())/(1000*3600*24);
		constructionPeriod = Integer.valueOf((int)longPeriod);
		record.setConstructionPeriod(constructionPeriod);
		
		//记录更新结果
		int result = 0;
		try {
			result = projectDetailMapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			logger.error("更新项目失败!"+e.getMessage());
		}
		if(result == 1){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean topProjectById(Long id, Integer isTop) {
		//置顶结果
		int result = 0;
		ProjectDetail project = new ProjectDetail();
		project.setId(id);
		project.setIsTop(isTop);
		try {
			result = projectDetailMapper.updateByPrimaryKeySelective(project);
		} catch (Exception e) {
			logger.error("按id置顶项目失败!"+e.getMessage());
		}
		if(result == 1){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public ProjectDetail selectByPrimaryKey(Long id){
		ProjectDetail project = projectDetailMapper.selectByPrimaryKey(id);
		return  project;
	}
	

	@Override
	public List<BasicProjectForm> selectBasicProjectsSelective(ProjectDetail projectDetail,
			ScreeningConditions screeningConditions,int... statuses) {
		//存查询结果
		List<BasicProjectForm> projects = null;
		if(projectDetail != null && projectDetail.getProcessCode() != null) {
			statuses = new int[1];
			statuses[0] = projectDetail.getProcessCode().intValue();
		}
		//查询符合条件的项目列表
		try {
			projects = projectDetailMapper.selectBasicProjectsSelective(statuses,screeningConditions, projectDetail);
		} catch (Exception e) {
			logger.error("条件查询项目缩略信息失败!"+e.getMessage());
		}
//		//加载项目未选中的设计师
//		for(BasicProjectForm thisProject : projects) {
//			Integer[] codes = designerRelationshipService.getUnselectedDesigners(thisProject.getId());
//			List<String> descriptions = new ArrayList<>();
//			for(Integer code : codes) {
//				String description = DesignDataBaseCache.getDicProjectComponent(code).getDescription();
//				descriptions.add(description);
//			}
//			thisProject.setUnSelectedDesigners(descriptions.toArray(new String[descriptions.size()]));
//		}
		
//		//加载项目未选中的设计师
//		for(BasicProjectForm thisProject : projects) {
//			Integer[] codes = designerRelationshipService.getUnselectedDesignersByProjectIdAndComponent(
//					thisProject.getComponentCode(), thisProject.getId());
//			List<String> descriptions = new ArrayList<>();
//			for(Integer code : codes) {
//				String description = DesignDataBaseCache.getDicProjectComponent(code).getDescription();
//				descriptions.add(description);
//			}
//			thisProject.setUnSelectedDesigners(descriptions.toArray(new String[descriptions.size()]));
//		}
		return projects;
	}

	
	@Override
	public boolean updateById(ProjectDetail projectDetail) {
		int result = 0;
		try {
			result = projectDetailMapper.updateByPrimaryKeySelective(projectDetail);
		} catch (Exception e) {
			logger.error("按id更新项目失败!"+e.getMessage());
		}
		if(result == 1){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int updateByPrimaryKeySelective(ProjectDetail project) {
		return projectDetailMapper.updateByPrimaryKeySelective(project);
	}

	@Override
	public Boolean isHavaPermission(Long userId, Long projectId) {
		if(userId == null || projectId == null){
			return false;
		}
		ProjectDetail project = null;
		try {
			project = projectDetailMapper.selectByPrimaryKey(projectId);
		} catch (Exception e) {
			logger.error("按id查询项目失败!"+e.getMessage());
		}
		//如果允许查看为1则允许所有人下载,返回true
		if(project != null && "1".equals(project.getAllowView())) {
			return true;
		}
		//如果为1则查看该设计师是否被该项目选中,或者登录者是否为项目
		//是发布人本人
		if(userId.equals(project.getUserId())) {
			return true;
		}
		//判断是否是选中设计师
		int count = 0;
		//set查询条件
		DesignerRelationship record = new DesignerRelationship();
		record.setProjectId(projectId);
		record.setUserId(userId);
		record.setIsSelected(1);
		try {
			count = designerRelationshipMapper.isSelected(record);
		} catch (Exception e) {
			logger.error("根据设计师和项目count失败!"+e.getMessage());
		}
		//大于零说明该设计师被选中
		if(count > 0) {
			return true;
		}
		return false;
	}
	
	@Transactional
	@Override
	public void updateProject(ProjectDetail project, String[] designerTypes, MultipartFile[] files, 
			MoneyListForm moneyListForm, MultipartFile image,String deleteIds) {
		String  designerType= StringUtils.join(designerTypes,",");
		project.setComponentCode(designerType);
		//计算工期
		project.setConstructionPeriod(DateUtil.differentDays(project.getStartupDate(),project.getDeadline()));
		if(image != null && StringUtils.isNotBlank(image.getOriginalFilename())){
			//上传到阿里云
			String coverImagePath = DesignFileUtils.uploadSingleFile(image);
			if(StringUtils.isNoneBlank(coverImagePath)){
				project.setCoverImage(coverImagePath);
			}
		}
		updateByPrimaryKeySelective(project);

		//插入阶段的费用划分
		updateByProjectIdSelective(moneyListForm.getMoneyStage1(project.getId()));
		updateByProjectIdSelective(moneyListForm.getMoneyStage2(project.getId()));


		for(MultipartFile file : files) {
			if(StringUtils.isNotBlank(file.getOriginalFilename())){
				//上传到阿里云
				String coverImagePath = DesignFileUtils.uploadSingleFile(file);
				if(StringUtils.isNoneBlank(coverImagePath)){
					projectFilesService.insert(new ProjectFiles(project.getId(),coverImagePath,file.getOriginalFilename()));
				}
			}
		}
		if(StringUtils.isNoneBlank(deleteIds)){
			String[] strIds = deleteIds.split(",");
			Long[] longIds = new Long[strIds.length];
			for (int i = 0; i < strIds.length; i++) {
				longIds[i] = Long.valueOf(strIds[i]);
			}
			projectFilesMapper.deleteByIds(longIds);
		}

	}
	
	@Override
    public int updateByProjectIdSelective(MoneyDistribution record) {
        return moneyDistributionMapper.updateByProjectIdSelective(record);
    }
}
