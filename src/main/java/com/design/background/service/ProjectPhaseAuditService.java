package com.design.background.service;

import java.util.List;

import com.design.background.entity.PhaseAuditFiles;
import com.design.background.entity.ProjectPhaseAudit;
import com.design.background.form.ProjectPhaseAuditForm;
import com.design.background.form.ProjectStageForm;

/**
 * @author 孟晓冬
 *项目阶段审核相关服务
 */
public interface ProjectPhaseAuditService {


	ProjectPhaseAudit selectByPrimaryKey(Long id);


	/**
	 * @Description: 根据项目ID和阶段状态码查询验收信息
	 * @Param:
	 * @return:
	 * @Author: 李紫林
	 * @Date: 2019/2/27
	 */
	List<ProjectPhaseAudit> selectByProjectIdAndStageCode(Long projectId,Integer stageCode);


	int updateByPrimaryKeySelective(ProjectPhaseAudit record);
	
	/**
	 * 根据筛选条件获取阶段审核列表
	 * @author 孟晓冬
	 * @param phaseAudit
	 * @return
	 */
	List<ProjectPhaseAuditForm> getPhaseAuditsSelective(ProjectPhaseAuditForm phaseAudit);
	
	/**
	 * 根据项目id获取阶段审核项
	 * @author 孟晓冬
	 * @param Long peojectId
	 * @return
	 */
	ProjectPhaseAuditForm getPhaseAuditByProjectId(Long projectId);
	
	/**
	 * 项目方通过阶段审核申请
	 * @author 孟晓冬
	 * @param Long peojectId
	 * @return
	 */
	Boolean projectorThroughApplication(Long projectId);
	
	/**
	 * 项目方拒绝阶段审核申请
	 * @author 孟晓冬
	 * @param Long peojectId
	 * @return
	 */
	Boolean projectorRejectApplication(Long projectId);

	/** 
	 * @Description: 用来展示一个项目的完成阶段等信息
	 * @Param:  
	 * @return:  
	 * @Author: 李紫林 
	 * @Date: 2019/2/20 
	 */
	ProjectPhaseAuditForm showStage(Long projectId);


	/**
	 * @Description: 发起阶段验收申请第一步，插入阶段验收表并获取插入后的ID
	 * @Param:
	 * @return:
	 * @Author: 李紫林
	 * @Date: 2019/2/20
	 */
	Long startStageCheckOne(ProjectPhaseAudit projectPhaseAudit);


	/**
	 * @Description: 发起阶段验收申请第二步，插入设计师上传文件表
	 * @Param:
	 * @return:
	 * @Author: 李紫林
	 * @Date: 2019/2/20
	 */
	int startStageCheckTwo(PhaseAuditFiles phaseAuditFiles);

	/**
	 * @Description: 根据项目ID判断项目是否已经发起验收
	 * @Param:
	 * @return:
	 * @Author: 李紫林
	 * @Date: 2019/2/21
	 */
	boolean isStartChecked(Long projectId);


}
