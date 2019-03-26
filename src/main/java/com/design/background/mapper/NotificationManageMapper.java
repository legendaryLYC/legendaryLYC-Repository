package com.design.background.mapper;

import java.util.Date;
import java.util.List;

import com.design.background.entity.NotificationManage;
import com.design.background.form.MessageForm;
import org.apache.ibatis.annotations.Param;

public interface NotificationManageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NotificationManage record);

    int insertSelective(NotificationManage record);

    NotificationManage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NotificationManage record);

    int updateByPrimaryKeyWithBLOBs(NotificationManage record);

    int updateByPrimaryKey(NotificationManage record);
    
    int insertSelectiveForm(MessageForm messageForm);
    
    public List<NotificationManage> selectByIsAll(NotificationManage record);
    
    public List<NotificationManage> selectByConditions(NotificationManage record);
    
    List<NotificationManage> selectByacceptUser(String acceptUser);

    List<NotificationManage> selectByacceptUserAndTime(@Param("acceptUser") String acceptUser,@Param("date") Date date);
}