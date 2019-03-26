package com.design.background.service.impl;

import com.design.background.entity.PhaseAuditFiles;
import com.design.background.mapper.PhaseAuditFilesMapper;
import com.design.background.service.PhaseAuditFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/2/21
 * @Description：
 */
@Service("phaseAuditFilesService")
public class PhaseAuditFilesServiceImpl implements PhaseAuditFilesService {

    @Autowired
    private PhaseAuditFilesMapper phaseAuditFilesMapper;


    @Override
    public int insertSelective(PhaseAuditFiles phaseAuditFiles) {
        return phaseAuditFilesMapper.insertSelective(phaseAuditFiles);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return phaseAuditFilesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PhaseAuditFiles> selectByProjectIdAndDesignerId(Long projectId, Long designerId, Integer stageCode) {
        return phaseAuditFilesMapper.selectByProjectIdAndDesignerId(projectId,designerId,stageCode);
    }
}
