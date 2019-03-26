package com.design.background.mapper;

import com.design.background.entity.DicProjectComponent;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;


public interface DicProjectComponentMapper {
	@Cacheable(value = "default",keyGenerator = "classKey")
	List<DicProjectComponent> selectAllProjectComponent();
	
	@Cacheable(value = "default",keyGenerator = "classKey")
    List<DicProjectComponent> selectAll();

	List<DicProjectComponent> selectProjectComponent(@Param("description") String description);

	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean deleteById(int id);

	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean insertProjectComponent(@Param("code") int code, @Param("description") String description);

	@CacheEvict(value = "default",keyGenerator = "classKey")
	boolean insertProjectComponentSelective(DicProjectComponent projectComponent);

	DicProjectComponent selectByPrimaryKey(Long id);

	DicProjectComponent selectProjectComponentByDescription(@Param("code") int code, @Param("description") String description);

	/**
	 * 由code值获取字典对象
	 * @author 孟晓冬
	 * @param code
	 * @return
	 */
	DicProjectComponent selectByCode(Integer code);
	
	/**
	 * 由code值获取字典描述
	 * @author 孟晓冬
	 * @param code
	 * @return
	 */
	String selectDescriptionByCode(Integer code);
}
