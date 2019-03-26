package com.design.background.mapper;



import com.design.background.entity.ExampleProject;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface ExampleProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExampleProject record);

    int insertSelective(ExampleProject record);

    ExampleProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExampleProject record);

    int updateByPrimaryKeyWithBLOBs(ExampleProject record);

    int updateByPrimaryKey(ExampleProject record);

    List<ExampleProject> selectExample();

    List<ExampleProject> selectExampleName(@Param("exampleName")String exampleName,@Param("startDate")Date startDate,@Param("endDate")Date endDate); // 根据项目名查询案例

    List<ExampleProject> selectSomeExample(); // 查询固定几条案例记录
    /**
     * @Title: selectClassicExample
     * @Description: 查询前台经典案例
     * @Author: lxt
     * @Date: 2019-02-16 14:43
     * @return: java.util.List<com.design.background.entity.ExampleProject>
     * @throws:
     */
    List<ExampleProject> selectClassicExample();

    List<ExampleProject> selectNewExample(@Param("startNum")Integer startNum,@Param("size")Integer size);  // 查询最新几条案例项目
}