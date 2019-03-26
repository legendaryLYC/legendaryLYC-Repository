package com.design.background.service.impl;

import com.design.background.entity.ExampleProject;
import com.design.background.mapper.ExampleProjectMapper;
import com.design.background.mapper.SysUserMapper;
import com.design.background.service.ExampleProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author： LZL
 * @Date： 2019/2/13
 * @Description： This is a class
 */
@Service("exampleProjectService")
public class ExampleProjectServiceImpl implements ExampleProjectService {
    @Autowired
    private ExampleProjectMapper exampleProjectMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return exampleProjectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ExampleProject record) {
        return exampleProjectMapper.insert(record);
    }

    @Override
    public int insertSelective(ExampleProject record) {
        return exampleProjectMapper.insertSelective(record);
    }

    @Override
    public ExampleProject selectByPrimaryKey(Long id) {
        ExampleProject exampleProject = exampleProjectMapper.selectByPrimaryKey(id);
        String userName = sysUserMapper.selectByPrimaryKey(exampleProject.getUserId()).getUsername();
        exampleProject.setUsername(userName);
        return exampleProject;
    }

    @Override
    public int updateByPrimaryKeySelective(ExampleProject record) {
        return exampleProjectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(ExampleProject record) {
        return exampleProjectMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(ExampleProject record) {
        return exampleProjectMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ExampleProject> selectExample() {
        // 查询此页面所有案例项目
        List<ExampleProject> result = exampleProjectMapper.selectExample();
        return result;
    }

    @Override
    public List<ExampleProject> selectExample(String exampleName, Date startDate, Date endDate) {
        // 根据项目名和日期查询案例项目
        List<ExampleProject> result = exampleProjectMapper.selectExampleName(exampleName, startDate ,endDate);
        return result;
    }

    @Override
    public List<ExampleProject> selectSomeExample() {
        // 查询前几条案例项目
        List<ExampleProject> result = exampleProjectMapper.selectSomeExample();
        return result;
    }

    /**
     * @Title: selectClassicExample
     * @Description:  查询前台经典案例
     * @Author: lxt
     * @Date: 2019-02-16 14:46
 * @return: java.util.List<com.design.background.model.ExampleModel>
     * @throws:
     */
    @Override
    public List<ExampleProject> selectClassicExample() {
        List<ExampleProject> result = exampleProjectMapper.selectClassicExample();
        return result;
    }

    @Override
    public List<ExampleProject> selectNewExample(Integer startNum,Integer size){
        List<ExampleProject> result2 = exampleProjectMapper.selectNewExample(startNum,size);
        return result2;
    }

}
