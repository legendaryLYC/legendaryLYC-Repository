package com.design.background.service;

import com.design.background.entity.RateManagement;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RateManagementService {
    List<RateManagement> selectAll(Integer code);
    int insert(RateManagement rateManagement);
    int deleteByPrimaryKey(Long id);
    RateManagement selectByPrimaryKey(Long id);
    int updateselective(RateManagement rateManagement);
}
