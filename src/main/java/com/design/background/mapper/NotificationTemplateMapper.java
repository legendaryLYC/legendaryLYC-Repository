package com.design.background.mapper;

import java.util.List;

import com.design.background.entity.NotificationManage;
import com.design.background.entity.NotificationTemplate;

public interface NotificationTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NotificationTemplate record);

    int insertSelective(NotificationTemplate record);

    NotificationTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NotificationTemplate record);

    int updateByPrimaryKeyWithBLOBs(NotificationTemplate record);

    int updateByPrimaryKey(NotificationTemplate record);
    
    NotificationTemplate selectByType(Long type);
    
    List<NotificationTemplate> selectAll(NotificationTemplate template);
}