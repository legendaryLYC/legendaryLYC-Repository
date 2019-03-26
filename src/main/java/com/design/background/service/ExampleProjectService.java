package com.design.background.service;


import com.design.background.entity.ExampleProject;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author： LZL
 * @Date： 2019/2/13
 * @Description： 这是案例项目的service
 */

@Transactional
public interface ExampleProjectService {
    int deleteByPrimaryKey(Long id);

    int insert(ExampleProject record);

    int insertSelective(ExampleProject record);

    ExampleProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExampleProject record);

    int updateByPrimaryKeyWithBLOBs(ExampleProject record);

    int updateByPrimaryKey(ExampleProject record);

    List<ExampleProject> selectExample();

    List<ExampleProject> selectExample(String exampleName, Date startDate, Date endDate);

    List<ExampleProject> selectSomeExample();
    /**
     * @Title: 查询前台经典案例
     * @Description:
     * @Author: lxt
     * @Date: 2019-02-16 15:36
     * @return: java.util.List<com.design.background.entity.ExampleProject>
     * @throws:
     */
    List<ExampleProject> selectClassicExample();

    List<ExampleProject> selectNewExample(Integer startNum,Integer size);
}
