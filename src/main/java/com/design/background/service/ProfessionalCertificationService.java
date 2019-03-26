package com.design.background.service;

import com.design.background.entity.ProfessionalCertification;

import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/9
 * @Description：
 */
public interface ProfessionalCertificationService {
    int deleteByPrimaryKey(Long id);

    int insert(ProfessionalCertification record);

    int insertSelective(ProfessionalCertification record);

    ProfessionalCertification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProfessionalCertification record);

    int updateByPrimaryKey(ProfessionalCertification record);

    List<ProfessionalCertification> selectByQualifyId(Long qualifyId);
}
