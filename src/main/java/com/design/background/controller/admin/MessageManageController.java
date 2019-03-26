package com.design.background.controller.admin;

import java.sql.Timestamp;
import java.util.List;

import org.apache.catalina.startup.RealmRuleSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.background.entity.DesigntypeExtend;
import com.design.background.entity.NotificationManage;
import com.design.background.entity.NotificationTemplate;
import com.design.background.entity.NotificationView;
import com.design.background.entity.SysUser;
import com.design.background.form.MessageForm;
import com.design.background.mapper.DesigntypeExtendMapper;
import com.design.background.mapper.NotificationViewMapper;
import com.design.background.mapper.SysUserMapper;
import com.design.background.mapper.UserMapper;
import com.design.background.model.ResultData;
import com.design.background.service.MessageManageService;
import com.design.background.util.Utility;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Author：周天
 * @Date： 2019/2/13
 * @Description：平台信息管理和发布的controller层
 */

@Controller
@Transactional
@RequestMapping("/admin/message")
public class MessageManageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageManageController.class);
	
	@Autowired
	MessageManageService messageManageService;
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private NotificationViewMapper notificationViewMapper;
    /**
     * @Description: 展示通知列表的controller
     * @Param: []
     * @return: java.lang.String
     * @Author: 周天
     * @Date: 2019/2/13
     */
	@RequestMapping(value = {"","/"})
	public String showALLMessage  (Model model ,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo ,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize ,
			NotificationManage message) {
		PageHelper.startPage(pageNo, pageSize);
		List<NotificationManage> messageList = messageManageService.showAllMessage(message);
		//进行分页显示的代码部分
		PageInfo<NotificationManage>  pageInfo = new PageInfo<>(messageList);
		if (pageNo > pageInfo.getPages()) {
			pageNo = pageInfo.getPages();
			PageHelper.startPage(pageNo, pageSize);
			messageList = messageManageService.showAllMessage(message);
			pageInfo = new PageInfo<NotificationManage>(messageList);
		}
		model.addAttribute("message", message);
		model.addAttribute("pageInfo", pageInfo);
        return "admin/message/list";
    }
	/**
	 * 进入通告发布页面,添加新的通告
	 *  时间:2019/2/14
	 * 最后修改时间:2019/2/14
	 * 注意事项:无
	* @author 周天
	*
	*/
	@RequestMapping(value = "/addMessageHtml")
	public String addMessageHtml(Model model) {
		return "admin/message/add";
	}
	
	/**
	 * 在发布通告页面,添加新的通告
	 *  时间:2019/2/14
	 * 最后修改时间:2019/2/14
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = {"/addMessage"})
	public ResultData addMessage(NotificationManage message) {
		
		// 获取操作的用户id存到对象的sender
		String loginUserName = Utility.getCurrentUsername();
		SysUser loginUser = userMapper.selectByUsername(loginUserName);
		message.setSender(loginUser.getUsername());
		// 获取当前的时间存储到数据库中
		java.sql.Timestamp date = new Timestamp(System.currentTimeMillis());
		message.setCreatTime(date);
		 ResultData result = new ResultData<Integer>();
		 int flag = messageManageService.allMessageAdd(message);
		 if(flag > 0) {
			 result.setCode("000000");
		 }
		 else {
			 result.setMessage("添加失败");
		 }
		return result;
	}
	/**
	 * 进入通告删除操作,删除单一或选定的一些的通告
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/13
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = { "/deleteMessage"}, produces = "application/json", consumes = "application/json")
	public ResultData deleteMessage(@RequestBody Long[] id ) {	
		 ResultData result = new ResultData<Integer>();
		int flag = -1;
		for(int i = 0;i < id.length; i++) { // 循环遍历传过来需要删除的id数组
			flag = messageManageService.delateMessage(id[i]);
			//删除已读信息表
			if(notificationViewMapper.selectreadedMessageBymessageId(id[i]) != null) {
				notificationViewMapper.deleteBymessageid(id[i]);
			}
			if(flag <= 0)
			{
				logger.info("====调用deletePhone方法删除操作失败====");
				return result;
			}
		}
		result.setCode("000000");
		return result;
	}
	
	/**
	 * 进入通告编辑页面,编辑通告信息
	 *  时间:2019/2/14
	 * 最后修改时间:2019/2/14
	 * 注意事项:无
	* @author 周天
	*
	*/
	@RequestMapping(value = "/editMessageHtml/{id}")
	public String editMessageHtml(Model model, @PathVariable("id") Long id) {		
		NotificationManage message = messageManageService.selectById(id); // 通过分割得到的id进行查找		
		model.addAttribute("message", message);
		return "admin/message/edit";
	}
	
	/**
	 * 进入通知信息更新操作,更新单一通知
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/10
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = { "/updateMessage"} , produces = "application/json;charset=UTF-8",method ={RequestMethod.POST})
	public ResultData updateMessage(NotificationManage message) {
		ResultData result = new ResultData<Integer>();
		int flag = -1;
		 flag = messageManageService.updateMessage(message);
		if(flag <= 0) {
			return result;
		}else {
			result.setCode("000000");
		}
		return result;
	}
	
    /**
     * @Description: 展示通知模版管理列表的方法
     * @Param: []
     * @return: java.lang.String
     * @Author: 周天
     * @Date: 2019/2/13
     */
	@RequestMapping(value = {"/template"})
	public String template  (Model model ,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo ,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize ,
			NotificationTemplate template) {
		PageHelper.startPage(pageNo, pageSize);
		List<NotificationTemplate> templateList = messageManageService.showAllTemplate(template);
		//进行分页显示的代码部分
		PageInfo<NotificationTemplate>  pageInfo = new PageInfo<>(templateList);
		if (pageNo > pageInfo.getPages()) {
			pageNo = pageInfo.getPages();
			PageHelper.startPage(pageNo, pageSize);
			templateList = messageManageService.showAllTemplate(template);
			pageInfo = new PageInfo<NotificationTemplate>(templateList);
		}
		model.addAttribute("template", template);
		model.addAttribute("pageInfo", pageInfo);
        return "admin/message/template/template";
    }
	
	/**
	 * 进入模版编辑页面,编辑模版信息
	 *  时间:2019/2/14
	 * 最后修改时间:2019/2/14
	 * 注意事项:无
	* @author 周天
	*
	*/
	@RequestMapping(value = "template/editTemplateHtml/{id}")
	public String editTemplateHtml(Model model, @PathVariable("id") Long id) {		
		NotificationTemplate template = messageManageService.selectTemplateById(id); // 通过分割得到的id进行查找		
		model.addAttribute("template", template);
		return "admin/message/template/edit";
	}
	
	/**
	 * 进入模版信息更新操作,更新单一模版
	 *  时间:2019/1/10
	 * 最后修改时间:2019/1/10
	 * 注意事项:无
	* @author 周天
	*
	*/
	@ResponseBody
	@RequestMapping(value = { "template/updateTemplate"} , produces = "application/json;charset=UTF-8",method ={RequestMethod.POST})
	public ResultData updateTemplate(NotificationTemplate template) {
		ResultData result = new ResultData<Integer>();
		int flag = messageManageService.updateTemplate(template);
		if(flag <= 0) {
			return result;
		}else {
			result.setCode("000000");
		}
		return result;
	}
	
	
}
