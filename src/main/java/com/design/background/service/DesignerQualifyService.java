package com.design.background.service;

import com.design.background.entity.DesignerQualify;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/9
 * @Description：
 */
public interface DesignerQualifyService {

    int deleteByPrimaryKey(Long id);

    int insert(DesignerQualify record);

    int insertSelective(DesignerQualify record);

    DesignerQualify selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DesignerQualify record);

    int updateByPrimaryKey(DesignerQualify record);

    List<DesignerQualify> selectQualifyList(@Param("designerQualify") DesignerQualify designerQualify, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
