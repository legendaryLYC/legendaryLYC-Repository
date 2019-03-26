package com.design.background.controller.front;

import com.design.background.common.controller.BaseController;
import com.design.background.config.FrontConfig;
import com.design.background.entity.*;
import com.design.background.service.ApplicationService;
import com.design.background.service.MessageGetService;
import com.design.background.service.MessageManageService;
import com.design.background.service.ProjectManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     :高红亮
 * @ Date       :2019/2/18
 * @ Description:展示报名详情的controller
*/
@Controller
public class ApplicationController extends BaseController {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private MessageManageService messageManageService;
    @Autowired
    private ProjectManageService projectManageService;
    @Autowired
    private MessageGetService messageGetService;
    @Autowired
    private HttpSession httpSession;

    /**
     * @ param     :Model model,Long projectId
     * @ return    :返回报名详情列表List
     * @ Description:返回报名详情的方法
     * @ Date       :2019/2/18
    */
    @RequestMapping("/application")
    public String shows(Model model,Application project,
                        @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, // 页码
                        @RequestParam(value = "pageSize", required = false, defaultValue = "7") Integer pageSize) // 页面容纳条数
    {
        PageHelper.startPage(pageNo, pageSize);
        List<Application> showList = applicationService.selectApplication(project);
        PageInfo<Application> pageInfo = new PageInfo<Application>(showList);
        if (pageNo > pageInfo.getPages()) {
            pageNo = pageInfo.getPages();
            PageHelper.startPage(pageNo, pageSize);
            pageInfo = new PageInfo<Application>(showList);
        }
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("nav","centerli1");
        LeadingUser leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
        String acceptUser = leadingUser.getTel();
        List<NotificationManage> messagelist = messageGetService.selectByacceptUser(acceptUser,leadingUser.getCreateTime());
        //查找已读消息
        List<NotificationView> readedlist = messageGetService.selectreadedMessage(leadingUser.getId(),leadingUser.getCreateTime());
        model.addAttribute("noRead",messagelist.size()-readedlist.size());
        return "front/center/application_detail";
    }

    /**
     * @ param     :Model model,Long projectId
     * @ return    :返回已选择设计师列表List
     * @ Description:返回已选择的设计师的方法
     * @ Date       :2019/2/19
    */
    @RequestMapping("/acceptlist")
    public String accept(Model model,Long projectId,
                        @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, // 页码
                        @RequestParam(value = "pageSize", required = false, defaultValue = "7") Integer pageSize) // 页面容纳条数
    {
        boolean has_leading = false ;
        PageHelper.startPage(pageNo, pageSize);
        List<Application> showList2 = applicationService.selectAccept(projectId);
        for(int i = 0;i<showList2.size();i++ ){
            if(showList2.get(i).getIsSelected() == 3){
                has_leading = true ;
            }
        }
        PageInfo<Application> pageInfo = new PageInfo<Application>(showList2);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("has_leading",has_leading);
        model.addAttribute("nav","centerli1");
        LeadingUser leadingUser=new LeadingUser();
        leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
        String acceptUser = leadingUser.getTel();
        List<NotificationManage> messagelist = messageGetService.selectByacceptUser(acceptUser,leadingUser.getCreateTime());
        //查找已读消息
        List<NotificationView> readedlist = messageGetService.selectreadedMessage(leadingUser.getId(),leadingUser.getCreateTime());
        model.addAttribute("noRead",messagelist.size()-readedlist.size());
        return "front/center/desginerList";
    }

    /**
     * @ param     :Long projectId
     * @ return    :int
     * @ Description:接受设计师
     * @ Date       :2019/2/19
    */
    @ResponseBody
    @RequestMapping("/accept")
    public String accept2(Long projectId,Long userId,Integer typeCode)
    {
        boolean refuse = applicationService.refuseSameTypeDesigners(projectId,typeCode,userId);
        int showList = applicationService.updateAccept(projectId,userId);
        if(showList==1){
        return "1";
        }
        else {
            return "2";
        }
    }
    /**
     * @ param     :Long projectId,Long userId
     * @ return    :int
     * @ Description:取消设计师
     * @ Date       :2019/2/21
    */
    @ResponseBody
    @RequestMapping("/refuse")
    public String remove(Long projectId,Long userId,String tel, String userName)
    {
        ProjectDetail project = projectManageService.selectByPrimaryKey(projectId);
        String projectName = project.getName();
        // 调用消息接口
        Long type = 1020L;
        Map<String,String> para= new HashMap();
        para.put("${userName}",userName);
        para.put("${projectName}",projectName);
        boolean messageResult = messageManageService.allNoticeAdd(tel,type, para);
        // 调用接口结束
        int showList = applicationService.updateRefuse(projectId,userId);
        return "1";
    }

    /**
    * @Author: 李永成
    * @Date: 2019/2/25
    * @Description: 更改项目组长
    * @Param:  peojectId,userId
    * @return:  1
    */
    @ResponseBody
    @RequestMapping(value = "/updateleader")
    public String updateLeader(Long projectId,Long userId,String tel,String userName){
        try{
            boolean resultReset = applicationService.resetLeader(projectId);
            boolean resultUpdate = applicationService.updateLeader(projectId,userId);
            // 调用消息接口
            Long type = 1017L;
            Map<String,String> para= new HashMap();
            para.put("${userName}",userName);
            para.put("${message}","您已经被选为项目组长");
            boolean messageResult = messageManageService.allNoticeAdd(tel,type, para);
            // 调用接口结束
            if(resultReset ==true && resultUpdate == true){
                return "1";
            }
        }
        catch (Exception e){
            return "2";
        }
        return "2";
    }

    /**
    * @Author: 李永成
    * @Date: 2019/2/25
    * @Description:  设置项目组长
    * @Param:  peojectId,userId
    * @return:  1
    */
    @ResponseBody
    @RequestMapping("/settingleader")
    public String settingLeader(Long projectId,Long userId,String tel, String userName){
        try{
            boolean resultUpdate = applicationService.updateLeader(projectId,userId);
            // 调用消息接口
            Long type = 1017L;
            Map<String,String> para= new HashMap();
            para.put("${userName}",userName);
            para.put("${message}","您已经被选为项目组长");
            boolean messageResult = messageManageService.allNoticeAdd(tel,type, para);
            // 调用接口结束
            if(resultUpdate == true){
                return "1";
            }
        }
        catch (Exception e){
            return "2";
        }
        return "2";
    }
}
