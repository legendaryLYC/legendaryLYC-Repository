package com.design.background.mapper;

import com.design.background.entity.DicProjectStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public interface DicProjectStatusMapper {
	@Cacheable(value = "default",keyGenerator = "classKey")
	List<DicProjectStatus> selectAllProjectStatus();

	@Cacheable(value = "default",keyGenerator = "classKey")
    List<DicProjectStatus> selectAll();

	List<DicProjectStatus> selectProjectStatus(@Param("description") String description);

	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean deleteById(int id);

	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean insertProjectStatus(@Param("code") int code, @Param("description") String description);

	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean insertProjectStatusSelective(DicProjectStatus projectStatus);

	DicProjectStatus selectByPrimaryKey(Long id);

	DicProjectStatus selectProjectStatusByDescription(@Param("code") int code, @Param("description") String description);

	String selectByCode(Integer code);
}
