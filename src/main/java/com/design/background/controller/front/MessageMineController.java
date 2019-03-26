package com.design.background.controller.front;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.design.background.common.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.background.config.FrontConfig;
import com.design.background.entity.LeadingUser;
import com.design.background.entity.NotificationManage;
import com.design.background.entity.NotificationView;
import com.design.background.form.DesginerLoginFrom;
import com.design.background.mapper.NotificationManageMapper;
import com.design.background.mapper.NotificationViewMapper;
import com.design.background.model.MyProjectModel;
import com.design.background.model.ResultData;
import com.design.background.service.DesignerRegisterService;
import com.design.background.service.MessageGetService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Author：宋博通
 * @Date： 2019/2/15
 * @Description： 这是个人中心我的消息的controller
 */
@Controller
@RequestMapping("center/MessageMine")
public class MessageMineController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(MessageMineController.class);
	
	@Autowired
	private MessageGetService messageGetService;
	
	@Autowired
	private NotificationManageMapper notificationManageMapper;
	
	@Autowired
	private NotificationViewMapper notificationViewMapper;
	
	@Autowired
	private HttpSession httpSession;
	//进入我的消息页面
    @RequestMapping("/one")
    public String MessageMine(Model model,HttpServletRequest Request,
    		@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
       		@RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize)
    {  
    	//获取登陆信息
    	LeadingUser leadingUser=new LeadingUser();
    	leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
    	String acceptUser = leadingUser.getTel();
    	PageHelper.startPage(pageNo, pageSize);

    	//查找全部消息
    	List<NotificationManage> messagelist = messageGetService.selectByacceptUser(acceptUser,leadingUser.getCreateTime());
    	//查找已读消息
    	List<NotificationView> readedlist = messageGetService.selectreadedMessage(leadingUser.getId(),leadingUser.getCreateTime());
    	//获取已读信息的id
    	try {
    		long [] ID=new long[readedlist.size()];
        	if(readedlist != null) {
        		for(int i = 0;i< readedlist.size();i++) {
        			ID[i] = readedlist.get(i).getMessageId();
        		}
        	}
        	model.addAttribute("readedId",ID);
		} catch (Exception e) {
			// TODO: handle exception
			long [] ID=new long[1];
			model.addAttribute("readedId",ID);
		}
    	
		/*
		 * //剩余未读信息 if(readedlist != null) { for(int i = 0;i< readedlist.size();i++) {
		 * for(int j = 0;j< messagelist.size();j++) {
		 * if(readedlist.get(i).getMessageId().equals(messagelist.get(j).getId()))
		 * messagelist.remove(j); } } }
		 */
    	PageInfo<NotificationManage> pageInfo = new PageInfo<NotificationManage>(messagelist);
    	if (pageNo > pageInfo.getPages()) {
    		pageNo = pageInfo.getPages();
			PageHelper.startPage(pageNo, pageSize);
			pageInfo = new PageInfo<NotificationManage>(messagelist);
		}
	   	model.addAttribute("pageInfo",pageInfo);
    	model.addAttribute("messagelist",messagelist);
    	model.addAttribute("noRead",pageInfo.getTotal()-readedlist.size());
		model.addAttribute("nav","centerli5");
    	return "front/center/myNew";
    }
    
    //进入消息详情页面
    @RequestMapping("/newdetail")
    public String MessageMineDetail(Model model,HttpServletRequest Request,Long id,Integer pageNo,Integer pageSize,Integer noRead)
    {	
    	System.out.println(id+" "+pageNo+" "+pageSize);
    	//获取登陆信息
    	LeadingUser leadingUser=new LeadingUser();
    	leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
    	//从全部信息查找
    	NotificationManage messagedetail = notificationManageMapper.selectByPrimaryKey(id);
    	//通过messageid从已读信息查找
    	NotificationView messageView = notificationViewMapper.selectreadedMessageBymessageId(id);
    	if( messageView !=null && messageView.getUserId().equals(leadingUser.getId())) {
    		System.out.println("此消息为已读");
    	}else {
    		//插入至已读
    		NotificationView readed = new NotificationView();
    		
    		readed.setUserId(leadingUser.getId());
    		readed.setMessageId(id);
    		//存入时间
    		Date time = new Date(); 
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    		
    		String create = sdf.format(time);
    		Date readtime = new Date(); 
    		try {
    			readtime = sdf.parse(create);
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            System.out.println(readtime);
            readed.setReadTime(readtime);
    		notificationViewMapper.insert(readed);
    	}
    	model.addAttribute("messagedetail",messagedetail);
    	model.addAttribute("nav","centerli5");
    	model.addAttribute("pageNo",pageNo);
    	model.addAttribute("pageSize",pageSize);
		model.addAttribute("noRead",noRead);
		
    	return "front/center/new_detail";
    }
    
}
