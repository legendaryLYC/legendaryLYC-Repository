package com.design.background.mapper;

import com.design.background.entity.DicProjectType;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public interface DicProjectTypeMapper {
	@Cacheable(value = "default",keyGenerator = "classKey")
	List<DicProjectType> selectAllProjectType();

	@Cacheable(value = "default",keyGenerator = "classKey")
    List<DicProjectType> selectAll();
	
	@CacheEvict(value = "default",keyGenerator = "classKey")
	List<DicProjectType> selectProjectType(@Param("description") String description);
	
	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean deleteById(int id);
	
	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean insertProjectType(@Param("code") int code, @Param("description") String description);

	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean insertProjectTypeSelective(DicProjectType projectType);

	DicProjectType selectByPrimaryKey(Long id);

	DicProjectType selectProjectTypeByDescription(@Param("code") int code, @Param("description") String description);

	DicProjectType selectByCode(Integer code);
}
