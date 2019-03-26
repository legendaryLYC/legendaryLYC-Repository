package com.design.background.mapper;

import com.design.background.entity.DicProjectStage;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DicProjectStageMapper {
	List<DicProjectStage> selectAllProjectStage();

    List<DicProjectStage> selectAll();

	List<DicProjectStage> selectProjectStage(@Param("description") String description);

	boolean deleteById(int id);

	boolean insertProjectStage(@Param("code") int code, @Param("description") String description);

	boolean insertProjectStageSelective(DicProjectStage projectStage);

	DicProjectStage selectByPrimaryKey(Long id);

	DicProjectStage selectProjectStageByDescription(@Param("code") int code, @Param("description") String description);
}
