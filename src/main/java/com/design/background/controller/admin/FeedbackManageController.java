package com.design.background.controller.admin;

import com.design.background.entity.FeedbackWithBLOBs;
import com.design.background.model.ResultData;
import com.design.background.service.FeedbackService;
import com.design.background.service.MessageManageService;
import com.design.background.util.StringToDate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author： 李紫林
 * @Date： 2019/2/25
 * @Description： 用来在后台处理用户反馈的controller
 */
@Controller
@RequestMapping("admin/feedback")
public class FeedbackManageController {

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private MessageManageService messageManageService;

    @RequestMapping("/showlist")
    public String showList(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, // 页码
                           @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, // 页面容纳条数
                           @RequestParam(value = "feedbackTitle", required = false, defaultValue = "") String feedbackTitle,
                           @RequestParam(value = "startDate", required = false) String startDate, // 反馈发布开始日期范围
                           @RequestParam(value = "endDate", required = false) String endDate, // 反馈发布开始日期范围
                           @RequestParam(value = "handleStartDate", required = false) String handleStartDate, // 反馈处理开始日期范围
                           @RequestParam(value = "handleEndDate", required = false) String handleEndDate) // 反馈处理结束日期范围
    {
        PageHelper.startPage(pageNo, pageSize);
        List<FeedbackWithBLOBs> feedbackWithBLOBsList = feedbackService.selectAllFeedback(feedbackTitle, StringToDate.toUtilDate(startDate),StringToDate.toUtilDate(endDate),StringToDate.toUtilDate(handleStartDate),StringToDate.toUtilDate(handleEndDate));
        PageInfo<FeedbackWithBLOBs> pageInfo = new PageInfo<FeedbackWithBLOBs>(feedbackWithBLOBsList);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/feedback/feedback_list";
    }

    @RequestMapping("/toreply")
    public String toReply(Model model,@RequestParam(value = "id") Long id,@RequestParam(value = "tel") String tel)
    {
        model.addAttribute("id",id);
        model.addAttribute("tel",tel);
        return "admin/feedback/feedback_reply";
    }

    @ResponseBody
    @RequestMapping(value = "/reply",produces = "application/json;charset=UTF-8")
    public ResultData reply(Model model,
                        @RequestParam(value = "id", required = false) Long id,
                        @RequestParam(value = "reply", required = false) String reply,
                        @RequestParam(value = "tel", required = false) String tel)
    {
        FeedbackWithBLOBs feedbackWithBLOBs = new FeedbackWithBLOBs();
        feedbackWithBLOBs.setReply(reply);
        feedbackWithBLOBs.setId(id);
        feedbackWithBLOBs.setProcessTime(new Date());

        FeedbackWithBLOBs feedbackInfo = feedbackService.selectByPrimaryKey(id);

        // 调用消息接口
        String userName = feedbackInfo.getUserName();
        Long type = 1016L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,String> para= new HashMap();
        para.put("${userName}",userName);
        para.put("${submitDate}",sdf.format(feedbackInfo.getSubmitTime()));
        para.put("${reply}",reply);
        boolean messageResult = messageManageService.allNoticeAdd(tel,type, para);
        // 调用接口结束

        int resultCode = feedbackService.updateByPrimaryKeySelective(feedbackWithBLOBs);

        return ResultData.setCodeAndMessage(resultCode,"回复成功","回复失败");
    }
}
