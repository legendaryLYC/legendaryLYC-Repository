package com.design.background.service;

import com.design.background.entity.NotificationTemplate;
import com.design.background.form.MessageForm;

import java.util.List;
import java.util.Map;

import com.design.background.entity.NotificationManage;

/**
 *平台消息前台显示及后台管理service层
 *  时间:2019/2/13
 * 最后修改时间:2019/2/13
 * 注意事项:无
* @author 周天
*
*/

public interface MessageManageService {

	
	/**
	 *消息通知提醒接口
	 *  时间:2019/2/13
	 * @param acceptUser 接收该条消息的用户 eg:13201525833
	 * @param type 该消息类型采用的模版类型  eg:1000
	 * @param para map类型 存储要替换模版的内容 eg: ${username} : 13201525833
	 * @return boolean 成功返回true，失败返回false
	* @author 周天
	*
	*/
	public boolean allNoticeAdd(String acceptUser,Long type ,Map<String,String> para);
	
	/**
	 *管理员发布全部人可见消息接口
	 *  时间:2019/2/13
	 * @param MeesageForm
	 * @return int
	* @author 周天
	*
	*/
	public int allMessageAdd(NotificationManage message);
	
	/**
	 *在管理页面显示所有的管理员发布的通知消息
	 *  时间:2019/2/13
	 * @param 
	 * @return List<NotificationManage>
	* @author 周天
	*
	*/
	public List<NotificationManage> showAllMessage(NotificationManage message);
	
	/**
	 *在管理页面编辑管理员之前发布的某一条通知消息
	 *  时间:2019/2/13
	 * @param NotificationManage
	 * @return int
	* @author 周天
	*
	*/
	public int updateMessage(NotificationManage message);
	
	/**
	 *在管理页面删除管理员发布的某一条通知消息
	 *  时间:2019/2/13
	 * @param NotificationManage
	 * @return int
	* @author 周天
	*
	*/
	public int delateMessage(Long id);
	
	
	/**
	 *在模版管理页面显示模版内容
	 *  时间:2019/2/13
	 * @param 
	 * @return List<NotificationTemplate>
	* @author 周天
	*
	*/
	public List<NotificationTemplate> showAllTemplate(NotificationTemplate template);
	
	/**
	 *在模版管理页面修改模版内容
	 *  时间:2019/2/13
	 * @param NotificationManage
	 * @return int
	* @author 周天
	*
	*/
	public int updateTemplate(NotificationTemplate template);
	
	/**
	 *显示某一用户的所有通知
	 *  时间:2019/2/13
	 * @param NotificationManage
	 * @return List<NotificationManage>
	* @author 周天
	*
	*/
	public List<NotificationManage> showUserMessage(String userName);

	/**
	 *显示某一条通知.进入编辑页面
	 *  时间:2019/2/13
	 * @param Long
	 * @return NotificationManage
	* @author 周天
	*
	*/
	NotificationManage selectById(Long id);
	
	/**
	 *显示某一条模版.进入编辑页面
	 *  时间:2019/2/13
	 * @param Long
	 * @return NotificationManage
	* @author 周天
	*
	*/
	NotificationTemplate selectTemplateById(Long id);
}
