package com.design.background.controller.front;

import com.design.background.common.controller.BaseController;
import com.design.background.config.FrontConfig;
import com.design.background.entity.FeedbackWithBLOBs;
import com.design.background.entity.LeadingUser;
import com.design.background.model.ResultData;
import com.design.background.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author： 李紫林
 * @Date： 2019/2/25
 * @Description： 用来处理前后台反馈的controller
 */
@Controller
public class FeedbackController extends BaseController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private FeedbackService feedbackService;

    /**
     * @Description: 用来提交用户反馈的controller
     * @Param: [title, content]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/2/25
     */
    @ResponseBody
    @RequestMapping(value = "/front/submitfeedback",produces = "application/json;charset=UTF-8")
    public ResultData submitFeedback(@RequestParam("title") String title,@RequestParam("content") String content)
    {
        LeadingUser leadingUser=new LeadingUser();
        leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);

        if(leadingUser == null)
        {
            return new ResultData("333333","请先登录");
        }

        FeedbackWithBLOBs feedbackWithBLOBs = new FeedbackWithBLOBs(null,leadingUser.getId(),title,new Date(),null,null,content,null);
        int resultCode = feedbackService.insertSelective(feedbackWithBLOBs);

        return ResultData.setCodeAndMessage(resultCode,"用户反馈提交成功！","用户反馈提交失败！");
    }
}
