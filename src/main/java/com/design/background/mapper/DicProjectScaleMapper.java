package com.design.background.mapper;

import com.design.background.entity.DicProjectScale;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DicProjectScaleMapper {
	List<DicProjectScale> selectAllProjectScale();

    List<DicProjectScale> selectAll();

	List<DicProjectScale> selectProjectScale(@Param("description") String description);

	boolean deleteById(int id);

	boolean insertProjectScale(@Param("code") int code, @Param("description") String description);

	boolean insertProjectScaleSelective(DicProjectScale projectScale);

	DicProjectScale selectByPrimaryKey(Long id);

	DicProjectScale selectProjectScaleByDescription(@Param("code")int code,@Param("description")String description);
}
