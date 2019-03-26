package com.design.background.mapper;

import com.design.background.entity.DicProjectFee;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public interface DicProjectFeeMapper {
	@Cacheable(value = "default",keyGenerator = "classKey")
	List<DicProjectFee> selectAllProjectFee();

	@Cacheable(value = "default",keyGenerator = "classKey")
    List<DicProjectFee> selectAll();

	List<DicProjectFee> selectProjectFee(@Param("description") String description);
	
	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean deleteById(int id);

	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean insertProjectFee(@Param("code") int code, @Param("description") String description);

	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean insertProjectFeeSelective(DicProjectFee projectFee);

	DicProjectFee selectByPrimaryKey(Long id);

	DicProjectFee selectProjectFeeByDescription(@Param("code") int code, @Param("description") String description);
}
