package com.design.background.service.impl;

import java.util.Date;
import java.util.List;
import com.design.background.entity.LeadingUser;
import com.design.background.entity.PhaseAuditFiles;
import com.design.background.entity.ProjectDetail;
import com.design.background.entity.ProjectPhaseAudit;
import com.design.background.mapper.LeadingUserMapper;
import com.design.background.mapper.PhaseAuditFilesMapper;
import com.design.background.mapper.ProjectDetailMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.design.background.form.ProjectPhaseAuditForm;
import com.design.background.mapper.ProjectPhaseAuditMapper;
import com.design.background.service.DesignerRelationshipService;
import com.design.background.service.ProjectPhaseAuditService;

/**
 * @author 孟晓冬
 *项目阶段审核相关服务
 */
@Service
public class ProjectPhaseAuditServiceImpl implements ProjectPhaseAuditService {
	
	@Autowired
	private ProjectPhaseAuditMapper projectPhaseAuditMapper;
	@Autowired
	private PhaseAuditFilesMapper phaseAuditFilesMapper;
	@Autowired
	private LeadingUserMapper leadingUserMapper;
	@Autowired
	private ProjectDetailMapper projectDetailMapper;
	@Autowired
	private DesignerRelationshipService designerRelationshipService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectPhaseAuditServiceImpl.class);


	@Override
	public ProjectPhaseAudit selectByPrimaryKey(Long id) {
		return projectPhaseAuditMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProjectPhaseAuditForm> getPhaseAuditsSelective(ProjectPhaseAuditForm phaseAudit) {
		//存查询结果
		List<ProjectPhaseAuditForm> audits = null;
		try {
			audits = projectPhaseAuditMapper.selectPhaseAudits(phaseAudit);
		} catch (Exception e) {
			logger.error("条件查询项目阶段审核失败!"+e.getMessage());
		}
		return audits;
	}

	@Override
	public ProjectPhaseAuditForm showStage(Long projectId) {
		return projectPhaseAuditMapper.selectStageByProjectId(projectId);
	}

	@Override
	public Long startStageCheckOne(ProjectPhaseAudit projectPhaseAudit) {
		projectPhaseAuditMapper.insertSelectiveAndReturnId(projectPhaseAudit);
		return projectPhaseAudit.getId();

	}

	@Override
	public int startStageCheckTwo(PhaseAuditFiles phaseAuditFiles) {
		return phaseAuditFilesMapper.insertSelective(phaseAuditFiles);
	}

	@Override
	public boolean isStartChecked(Long projectId) {
		return projectPhaseAuditMapper.selectFromAuditByProjectId(projectId) == null?false:true;
	}

	@Override
	public ProjectPhaseAuditForm getPhaseAuditByProjectId(Long projectId) {

		//存查询结果
		ProjectPhaseAuditForm result = null;
		List<PhaseAuditFiles> files = null;
		try {
			result = projectPhaseAuditMapper.selectPhaseAuditByProjectId(projectId);
			files = phaseAuditFilesMapper.selectByPhaseAuditId(result.getId());
		} catch (Exception e) {
			logger.error("根据id查询项目阶段审核或查询阶段文件失败!"+e.getMessage());
		}

		
		if(files != null && files.size() != 0) {
			result.setFiles(files);
			LeadingUser designer = null;
			try{
				designer = leadingUserMapper.selectByPrimaryKey(files.get(0).getUserId());
				result.setDesignerName(designer.getUsername());
			} catch (Exception e) {
				logger.error("根据id查询用户失败!"+e.getMessage());
			}
		}
		
		return result;
	}

	@Override
	public Boolean projectorThroughApplication(Long projectId) {
		if(projectId == null) {
			return false;
		}
		//查询用来更新状态的对象
		ProjectPhaseAudit record = null;
		//记录更新结果
		int result = 0;
		try {
			record = projectPhaseAuditMapper.selectByProjectId(projectId);
		} catch (Exception e) {
			logger.error("项目id查询项目阶段审核实体类失败!"+e.getMessage());
			return false;
		}
		//是第一阶段的申请,更新阶段
		if(record.getStageCode() == 1000) {
			//更新项目详情表中的阶段
			ProjectDetail project = new ProjectDetail();
			project.setStageCode(1001);
			project.setId(projectId);
			try {
				result = projectDetailMapper.updateByPrimaryKeySelective(project);
			} catch (Exception e) {
				logger.error("项目id更新项目详情失败!"+e.getMessage());
				return false;
			}
		}
		//已验收
		record.setReviewCode(1001);
		//审核时间
		record.setUpdateTime(new Date(System.currentTimeMillis()));
		try {
			result += projectPhaseAuditMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			logger.error("更新阶段审核信息失败!"+e.getMessage());
			return false;
		}
		//发送消息
		designerRelationshipService.examineProject(projectId, null);
		if(result == 2) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean projectorRejectApplication(Long projectId) {
		if(projectId == null) {
			return false;
		}
		//查询用来更新状态的对象
		ProjectPhaseAudit record = null;
		try {
			record = projectPhaseAuditMapper.selectByProjectId(projectId);
		} catch (Exception e) {
			logger.error("项目id查询项目阶段审核实体类失败!"+e.getMessage());
			return false;
		}
		record.setProjectId(projectId);
		//已验收
		record.setReviewCode(1002);
		//审核时间
		record.setUpdateTime(new Date(System.currentTimeMillis()));
		//记录更新结果
		int result = 0;
		try {
			result = projectPhaseAuditMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			logger.error("更新阶段审核信息失败!"+e.getMessage());
			return false;
		}
		if(result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int updateByPrimaryKeySelective(ProjectPhaseAudit record) {
		return projectPhaseAuditMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ProjectPhaseAudit> selectByProjectIdAndStageCode(Long projectId, Integer stageCode) {
		return projectPhaseAuditMapper.selectByProjectIdAndStageCode(projectId,stageCode);
	}
}
