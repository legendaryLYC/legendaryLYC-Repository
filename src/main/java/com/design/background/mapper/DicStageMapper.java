package com.design.background.mapper;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.design.background.entity.DicProjectStage;

public interface DicStageMapper {
	@CacheEvict(value = "default",keyGenerator = "classKey")
    int deleteByPrimaryKey(Long id);

    @CacheEvict(value = "default",keyGenerator = "classKey")
    int insert(DicProjectStage record);

    @CacheEvict(value = "default",keyGenerator = "classKey")
    int insertSelective(DicProjectStage record);

    DicProjectStage selectByPrimaryKey(Long id);

    @CacheEvict(value = "default",keyGenerator = "classKey")
    int updateByPrimaryKeySelective(DicProjectStage record);
    
    @CacheEvict(value = "default",keyGenerator = "classKey")
    int updateByPrimaryKey(DicProjectStage record);
    
    //获取该字典的全部内容
    @Cacheable(value = "default",keyGenerator = "classKey")
    List<DicProjectStage> selectAll();
}