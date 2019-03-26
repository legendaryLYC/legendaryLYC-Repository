package com.design.background.service.impl;

import com.design.background.entity.ProfessionalCertification;
import com.design.background.mapper.ProfessionalCertificationMapper;
import com.design.background.service.ProfessionalCertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/9
 * @Description：
 */
@Service
public class ProfessionalCertificationServiceImpl implements ProfessionalCertificationService {
    @Autowired
    private ProfessionalCertificationMapper professionalCertificationMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return professionalCertificationMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ProfessionalCertification record) {
        return professionalCertificationMapper.insert(record);
    }

    @Override
    public int insertSelective(ProfessionalCertification record) {
        return professionalCertificationMapper.insertSelective(record);
    }

    @Override
    public ProfessionalCertification selectByPrimaryKey(Long id) {
        return professionalCertificationMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ProfessionalCertification record) {
        return professionalCertificationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ProfessionalCertification record) {
        return professionalCertificationMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ProfessionalCertification> selectByQualifyId(Long qualifyId) {
        return professionalCertificationMapper.selectByQualifyId(qualifyId);
    }
}
