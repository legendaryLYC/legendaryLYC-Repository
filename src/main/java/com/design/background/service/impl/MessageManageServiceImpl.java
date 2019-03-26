package com.design.background.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.mail.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.entity.NotificationManage;
import com.design.background.entity.NotificationTemplate;
import com.design.background.entity.SysUser;
import com.design.background.form.MessageForm;
import com.design.background.mapper.NotificationManageMapper;
import com.design.background.mapper.NotificationTemplateMapper;
import com.design.background.mapper.NotificationViewMapper;
import com.design.background.mapper.SysUserMapper;
import com.design.background.service.MessageManageService;
import com.design.background.util.SendMessageUtil;
import com.design.background.util.Utility;

@Service("MessageManageService")
public class MessageManageServiceImpl implements MessageManageService {

	private static final Logger logger = LoggerFactory.getLogger(MessageManageServiceImpl.class);
	
	@Autowired
	private NotificationManageMapper notificationManageMapper;
	@Autowired
	private NotificationTemplateMapper notificationTemplateMapper;
	@Autowired
	private NotificationViewMapper notificationViewMapper;
	@Autowired
	private SysUserMapper userMapper;
	
	@Override
	public boolean allNoticeAdd(String acceptUser,Long type ,Map<String,String> para) {
		boolean flag = false;
		NotificationTemplate template = new NotificationTemplate();
		NotificationManage notificationMessage = new NotificationManage();
		 // 对传过来的通知通过通知类型找到对应的模版，再给模版添加相应的信息到message里
		template = notificationTemplateMapper.selectByType(type);
		if(template == null) {
			return flag; // 如果没有找到直接返回，返回false
		}
		notificationMessage = SendMessageUtil.sendMessage(acceptUser,template.getTemplate(), para); // 将模版和对应的参数传递给工具类方法，失败返回null
		if(notificationMessage == null) {
			return flag; // 调用sendMessage方法失败直接返回，返回flase
		}
		int insertIsSuccess = notificationManageMapper.insertSelective(notificationMessage);
		if(insertIsSuccess <= 0) {
			return flag; // 调用insertSelective方法失败直接返回，返回flase
		}
		flag = true;
		return flag;
	}
	

	@Override
	public int allMessageAdd(NotificationManage message) {
		int flag = notificationManageMapper.insertSelective(message);
		return flag;
	}
	
	@Override
	public NotificationManage selectById(Long id) {
		NotificationManage message = notificationManageMapper.selectByPrimaryKey(id);
		return message;
	}
	@Override
	public List<NotificationManage> showAllMessage(NotificationManage message) {
		List<NotificationManage> allMessage = notificationManageMapper.selectByIsAll(message);
		return allMessage;
	}

	@Override
	public int updateMessage(NotificationManage message) {
		int flag = notificationManageMapper.updateByPrimaryKeySelective(message);
		return flag;
	}

	@Override
	public int delateMessage(Long id) {
		int flag = notificationManageMapper.deleteByPrimaryKey(id);
		return flag;
	}

	@Override
	public List<NotificationTemplate> showAllTemplate(NotificationTemplate template) {
		List<NotificationTemplate> templateList = notificationTemplateMapper.selectAll(template);
		return templateList;
	}

	@Override
	public int updateTemplate(NotificationTemplate template) {
		int flag = notificationTemplateMapper.updateByPrimaryKeySelective(template);
		return flag;
	}

	@Override
	public List<NotificationManage> showUserMessage(String userName) {
		NotificationManage record = new NotificationManage();
		record.setAcceptUser(userName);
		List<NotificationManage> allMessage = notificationManageMapper.selectByConditions(record);
		return allMessage;
	}

	@Override
	public NotificationTemplate selectTemplateById(Long id) {
		NotificationTemplate template = notificationTemplateMapper.selectByPrimaryKey(id);
		return template;
	}
	
	
}
