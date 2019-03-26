package com.design.background.mapper;

import java.util.Date;
import java.util.List;

import com.design.background.entity.NotificationView;
import org.apache.ibatis.annotations.Param;

public interface NotificationViewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NotificationView record);

    int insertSelective(NotificationView record);

    NotificationView selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NotificationView record);

    int updateByPrimaryKey(NotificationView record);

    List<NotificationView> selectByUserid(Long userid);
    
    List<NotificationView> selectByUseridAndTime(@Param("userid") Long userid, @Param("date") Date date);
    
    NotificationView selectreadedMessageBymessageId(Long messageid);
    
    int deleteBymessageid(Long messageid);
}