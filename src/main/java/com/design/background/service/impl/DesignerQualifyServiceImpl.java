package com.design.background.service.impl;

import com.design.background.entity.DesignerQualify;
import com.design.background.mapper.DesignerQualifyMapper;
import com.design.background.service.DesignerQualifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/3/9
 * @Description：
 */
@Service
public class DesignerQualifyServiceImpl implements DesignerQualifyService {

    @Autowired
    private DesignerQualifyMapper designerQualifyMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return designerQualifyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DesignerQualify record) {
        return designerQualifyMapper.insert(record);
    }

    @Override
    public int insertSelective(DesignerQualify record) {
        return designerQualifyMapper.insertSelective(record);
    }

    @Override
    public DesignerQualify selectByPrimaryKey(Long id) {
        return designerQualifyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DesignerQualify record) {
        return designerQualifyMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DesignerQualify record) {
        return designerQualifyMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<DesignerQualify> selectQualifyList(DesignerQualify designerQualify, Date startTime, Date endTime) {
        return designerQualifyMapper.selectQualifyList(designerQualify,startTime,endTime);
    }
}
