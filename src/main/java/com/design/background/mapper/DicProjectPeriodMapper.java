package com.design.background.mapper;

import com.design.background.entity.DicProjectPeriod;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public interface DicProjectPeriodMapper {
	@Cacheable(value = "default",keyGenerator = "classKey")
	List<DicProjectPeriod> selectAllProjectPeriod();

	@Cacheable(value = "default",keyGenerator = "classKey")
    List<DicProjectPeriod> selectAll();

	List<DicProjectPeriod> selectProjectPeriod(@Param("description") String description);

	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean deleteById(int id);

	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean insertProjectPeriod(@Param("code") int code, @Param("description") String description);

	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean insertProjectPeriodSelective(DicProjectPeriod projectPeriod);

	DicProjectPeriod selectByPrimaryKey(Long id);

	DicProjectPeriod selectProjectPeriodByDescription(@Param("code") int code, @Param("description") String description);
}
