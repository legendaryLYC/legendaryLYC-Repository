package com.design.background.service;

import java.util.Date;
import java.util.List;

import com.design.background.entity.NotificationManage;
import com.design.background.entity.NotificationView;

/**
 * @Author： 宋博通
 * @Date： 2019/2/17
 * @Description： This is a class
 */
public interface MessageGetService {

	//获取消息信息
	public List<NotificationManage> selectByacceptUser(String  acceptUser);
	
	//获取已读消息
	public List<NotificationView> selectreadedMessage(Long  userid);


	//获取此时间之后的消息信息
	public List<NotificationManage> selectByacceptUser(String  acceptUser, Date date);

	//获取此时间之后已读消息
	public List<NotificationView> selectreadedMessage(Long  userid,Date date);

}

