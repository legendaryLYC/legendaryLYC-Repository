package com.design.background.mapper;

import com.design.background.entity.MoneyDistribution;

import java.util.List;

public interface MoneyDistributionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MoneyDistribution record);

    int insertSelective(MoneyDistribution record);

    MoneyDistribution selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MoneyDistribution record);

    int updateByPrimaryKey(MoneyDistribution record);

    int updateByProjectIdSelective(MoneyDistribution record);
    
    List<MoneyDistribution> selectByProjectId(Long projectId);

    MoneyDistribution selectByStage(Long projectId, int stageCode);

    MoneyDistribution findbyStage(MoneyDistribution MoneyDistribution);
}