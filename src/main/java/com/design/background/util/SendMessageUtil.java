package com.design.background.util;

import java.sql.Timestamp;
import java.util.Map;

import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;

import com.design.background.entity.NotificationManage;
import com.design.background.entity.NotificationTemplate;
import com.design.background.entity.SysUser;
import com.design.background.form.MessageForm;
import com.design.background.mapper.NotificationManageMapper;
import com.design.background.mapper.NotificationTemplateMapper;
import com.design.background.mapper.SysUserMapper;

/**
 * 用来发送消息提醒的工具类
 * @author 周天
 *
 */
public class SendMessageUtil {

	
	private static final String NOTIFYTYPE = "系统通知"; // 自动通知的通知类型默认全部为系统通知
	
	private static final int ISALL = 0; // 自动通知的通知值为 0 , 0代表系统自动发送的提醒 
	

	/**
	 * 通过传递过来的参数进行拼接，处理生成一个对象返回过去
	 * @param acceptUser 接收该消息的用户
	 * @param messageTemplate 要填充的通知模版内容
	 * @param para[] 模版内要填充的内容
	 * @return sendMessage 返回要插入到数据库notifacation_manage表中的对象
	 * @author 周天
	 *
	 */
	public static NotificationManage sendMessage( String acceptUser,String messageTemplate ,Map<String,String> para ) {
		
		NotificationManage notificationMessage = new NotificationManage(); // 创建一个返回消息的对象
		// 获取操作的用户username存到对象的sender
		String loginUserName = Utility.getCurrentUsername(); 
		// 获取当前的时间存储到数据库中
		java.sql.Timestamp date = new Timestamp(System.currentTimeMillis());
		// 将默认值和获取到的数据填充到当前对象内
		notificationMessage.setSender(loginUserName); // 获取当前操作用户的用户名存储到对象中
		notificationMessage.setCreatTime(date); // 设置创建时间
		notificationMessage.setAcceptUser(acceptUser); // 设置接收的用户
		notificationMessage.setIsall(ISALL); // 设置当前消息类型为系统自动提醒 默认值为 0
		notificationMessage.setNotifyType(NOTIFYTYPE); // 设置当前通知的通知类型 默认值为 "系统通知"
		notificationMessage.setMessage(spiltMessage(messageTemplate,para)); // 调用spiltMessage方法获取拼接好的通知内容
		notificationMessage.setTitle(notificationMessage.getMessage().substring(0, (int)(notificationMessage.getMessage().length()/4)));
		return notificationMessage;
	}
	public static String spiltMessage(String messageTemplate ,Map<String,String> para) {
		
		if(null != para) {
			// for循环处理map读取参数
			for(Map.Entry<String, String> entry :para.entrySet()) {
				messageTemplate = messageTemplate.replace(entry.getKey(), entry.getValue());
			}
		}
		
		return messageTemplate;
		
	}
	
}
