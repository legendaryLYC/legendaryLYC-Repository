package com.design.background.service.impl;

import com.design.background.entity.RateManagement;
import com.design.background.mapper.RateManagementMapper;
import com.design.background.service.RateManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 费率管理实现类
 * @author 任松
 */
@Service
public class RateManagementServiceImpl implements RateManagementService {
    @Autowired
    private RateManagementMapper rateManagementMapper;

    @Override
    public List<RateManagement> selectAll(Integer code) {
        return rateManagementMapper.selectAll(code);
    }

    @Override
    public int insert(RateManagement rateManagement) {
        return rateManagementMapper.insert(rateManagement);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return rateManagementMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RateManagement selectByPrimaryKey(Long id) {
        return rateManagementMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateselective(RateManagement rateManagement) {
        return rateManagementMapper.updateByPrimaryKeySelective(rateManagement);
    }
}
