package com.design.background.service.impl;

import com.design.background.entity.DicProjectScale;
import com.design.background.mapper.DicProjectScaleMapper;
import com.design.background.service.DicProjectScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("projectScaleService")
public class DicProjectScaleServiceImpl implements DicProjectScaleService {

    @Autowired
    DicProjectScaleMapper projectScaleMapper;


    @Cacheable(value = "default",keyGenerator = "classKey")
    @Override
    public List<DicProjectScale> getModels() {
        List<DicProjectScale> models = null;
        try {
            models = projectScaleMapper.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }

    @Override
    public List<DicProjectScale> selectProjectScale() {
        List<DicProjectScale> projectScaleList = null;
        try {
            projectScaleList = projectScaleMapper.selectAllProjectScale();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectScaleList;
    }

    @Override
    public List<DicProjectScale> selectProjectScale(String description) {
        List<DicProjectScale> projectScaleList = null;
        try {
            projectScaleList = projectScaleMapper.selectProjectScale(description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectScaleList;
    }

    @CacheEvict(value = "default",keyGenerator = "classKey")
    @Override
    public boolean deleteById(int id) {
        boolean flag = false;
        try {
            flag = projectScaleMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    @CacheEvict(value = "default",keyGenerator = "classKey")
    @Override
    public boolean insertProjectScale(int code, String name) {
        boolean flag = false;
        try {
            flag = projectScaleMapper.insertProjectScale(code, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean insertProjectScaleSelective(DicProjectScale projectScale) {
        boolean flag = false;
        try {
            flag = projectScaleMapper.insertProjectScaleSelective(projectScale);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public DicProjectScale selectByPrimaryKey(Long id) {
        DicProjectScale projectScale = projectScaleMapper.selectByPrimaryKey(id);
        return projectScale;
    }

    @Override
    public DicProjectScale selectProjectScaleByDescription(String description, int code) {
        DicProjectScale ProjectScale = projectScaleMapper.selectProjectScaleByDescription(code, description);
        return ProjectScale;
    }
}
