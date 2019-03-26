package com.design.background.service.impl;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.design.background.entity.NotificationManage;
import com.design.background.entity.NotificationView;
import com.design.background.mapper.NotificationManageMapper;
import com.design.background.mapper.NotificationViewMapper;
import com.design.background.service.MessageGetService;

/**
 * @Author：Song
 * @Date： 2019/2/17
 * @Description： This is a class
 */
@Service("MessageGetService")
public class MessageGetServiceImpl implements MessageGetService {
	private static final Logger logger = LoggerFactory.getLogger(MessageGetServiceImpl.class);
	
    @Autowired
    private NotificationManageMapper notificationManageMapper;
    
    @Autowired
    private NotificationViewMapper notificationViewMapper;
    
	@Override
	public List<NotificationManage> selectByacceptUser(String acceptUser) {
		// TODO Auto-generated method stub
		return notificationManageMapper.selectByacceptUser(acceptUser);
	}

	@Override
	public List<NotificationView> selectreadedMessage(Long userid) {
		// TODO Auto-generated method stub
		return notificationViewMapper.selectByUserid(userid);
	}

	@Override
	public List<NotificationManage> selectByacceptUser(String acceptUser, Date date) {
		return notificationManageMapper.selectByacceptUserAndTime(acceptUser, date);
	}

	@Override
	public List<NotificationView> selectreadedMessage(Long userid, Date date) {
		return notificationViewMapper.selectByUseridAndTime(userid,date);
	}
}
