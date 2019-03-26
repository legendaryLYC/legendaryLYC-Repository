package com.design.background.mapper;

import java.util.List;
import com.design.background.entity.ProjectPhaseAudit;
import com.design.background.form.ProjectPhaseAuditForm;
import org.apache.ibatis.annotations.Param;

public interface ProjectPhaseAuditMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectPhaseAudit record);

    int insertSelective(ProjectPhaseAudit record);

    ProjectPhaseAudit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectPhaseAudit record);

    int updateByPrimaryKey(ProjectPhaseAudit record);
    
    /**
     * 条件查询符合条件的阶段审核记录
     * @author 孟晓冬
     */
    List<ProjectPhaseAuditForm> selectPhaseAudits(ProjectPhaseAuditForm record);
    
    /**
	 * 根据项目id获取一条待审核阶段审核项
	 * @author 孟晓冬
	 * @param Long peojectId
	 * @return
	 */
	ProjectPhaseAuditForm selectPhaseAuditByProjectId(Long projectId);

	/**
	 * 根据项目id获取最新的一条阶段审核实体类
	 * @author 孟晓冬
	 * @param Long peojectId
	 * @return
	 */
	ProjectPhaseAudit selectByProjectId(Long projectId);
    
    /** 
     * @Description: 用来根据项目ID查询最新的一条项目阶段 
     * @Param:  
     * @return:  
     * @Author: 李紫林 
     * @Date: 2019/2/20 
     */
    ProjectPhaseAuditForm selectStageByProjectId(@Param("projectId") Long projectId);

    
    /** 
     * @Description: 插入记录并且返回插入的记录ID 
     * @Param:  
     * @return:  
     * @Author: 李紫林 
     * @Date: 2019/2/20 
     */
    Long insertSelectiveAndReturnId(ProjectPhaseAudit record);


    /**
     * @Description: 根据项目ID查询记录
     * @Param:
     * @return:
     * @Author: 李紫林
     * @Date: 2019/2/21
     */
    List<ProjectPhaseAuditForm> selectFromAuditByProjectId(@Param("projectId") Long projectId);


    List<ProjectPhaseAudit> selectByProjectIdAndStageCode(@Param("projectId")Long projectId, @Param("stageCode")Integer stageCode);
}