package com.design.background.mapper;

import java.util.List;

import com.design.background.entity.PhaseAuditFiles;
import org.apache.ibatis.annotations.Param;

public interface PhaseAuditFilesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PhaseAuditFiles record);

    int insertSelective(PhaseAuditFiles record);

    PhaseAuditFiles selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PhaseAuditFiles record);

    int updateByPrimaryKey(PhaseAuditFiles record);
    
    /**
     * 根据phaseAuditId查询所有阶段文件
     * @author 孟晓冬
     * @param phaseAuditId
     * @return
     */
    List<PhaseAuditFiles> selectByPhaseAuditId(Long phaseAuditId);


    List<PhaseAuditFiles> selectByProjectIdAndDesignerId(@Param("projectId") Long projectId, @Param("designerId")Long designerId, @Param("stageCode")Integer stageCode);
}