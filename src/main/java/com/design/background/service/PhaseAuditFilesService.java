package com.design.background.service;

import com.design.background.entity.PhaseAuditFiles;

import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/2/21
 * @Description：
 */
public interface PhaseAuditFilesService {

    int insertSelective(PhaseAuditFiles phaseAuditFiles);

    int deleteByPrimaryKey(Long id); // 根据主键删除

    List<PhaseAuditFiles> selectByProjectIdAndDesignerId(Long projectId,Long designerId,Integer stageCode);
}
