package com.design.background.controller.front;


import com.design.background.common.controller.BaseController;
import com.design.background.config.Constant;
import com.design.background.config.FrontConfig;
import com.design.background.entity.DesignerRelationship;
import com.design.background.entity.LeadingUser;
import com.design.background.entity.ProjectDetail;
import com.design.background.model.ProjectTypeModel;
import com.design.background.model.ResultData;
import com.design.background.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Author： 李紫林
 * @Date： 2019/2/18
 * @Description： 用于报名时展示项目欠缺设计师类型，设计师报名和取消报名的controller
 */
@Controller
@RequestMapping("/join")
public class JoinProjectController extends BaseController {
    @Autowired
    private ProjectManageService projectManageService;
    @Autowired
    private DicProjectComponentService dicProjectComponentService;
    @Autowired
    private DesignerRelationshipService designerRelationshipService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private LeadingUserProjectorService leadingUserProjectorService;
    @Autowired
    private MessageManageService messageManageService;
    @Autowired
    private ProjectDetailService projectDetailService;

    @RequestMapping("/showprojecttype")
    public String showProjectType(Model model,@RequestParam(value = "projectId") Long projectId) // 项目ID
    {
        LeadingUser leadingUser=new LeadingUser();
        leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
        if(leadingUser != null)
        {
            ProjectDetail project = projectManageService.selectByPrimaryKey(projectId);
            Integer[] majorArr = leadingUserProjectorService.selectDesignerMajorById(leadingUser.getId());
            Integer[] arr = designerRelationshipService.getUnselectedDesigners(projectId);
            List<ProjectTypeModel> typeList = new ArrayList<>();
            boolean flag = true;
            if(majorArr!=null)
            {
                //挑选出设计师自身有的专业并符合项目所剩专业
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < majorArr.length; j++) {
                        if (arr[i].equals(majorArr[j])) {
                            flag = false;
                            typeList.add(new ProjectTypeModel(arr[i], dicProjectComponentService.selectByCode(arr[i]).getDescription()));
                        }
                    }
                }
            }
            if (arr.length == 0 || flag) {
                // 如果没有待选择的专业了
                model.addAttribute("isEmpty", "1");
            } else if(majorArr == null) {
                // 如果设计师没在个人中心选过专业
                model.addAttribute("isEmpty", "2");
            }
            else
            {
                model.addAttribute("isEmpty", "0");
            }
            model.addAttribute("typeList", typeList);
            model.addAttribute("projectId", projectId);
            return "front/join_project";
        }
        return "front/login";
    }

    /**
     * @Description: 用来取消报名的controller
     * @Param: [model, projectId, typeCode]
     * @return: com.design.background.model.ResultData
     * @Author: 李紫林
     * @Date: 2019/2/18
     */
    @ResponseBody
    @RequestMapping(value = "/joinproject",produces = "application/json;charset=UTF-8")
    public ResultData joinProject(Model model,
                                  @RequestParam(value = "projectId") Long projectId, // 项目ID
                                  @RequestParam(value = "type") Integer typeCode) // 字典码
    {

        LeadingUser leadingUser=new LeadingUser();
        leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);

        if(!leadingUser.getRoleCode().equals(1001))
        {
            return ResultData.setCodeAndMessage(0,"请登录设计师账号来报名！","请登录设计师账号来报名！");
        }
        DesignerRelationship designerRelationship = new DesignerRelationship(projectId,leadingUser.getId(),typeCode, Constant.JOINRESULT_JOINED, new Date());
        int result = designerRelationshipService.insertSelective(designerRelationship);
        // 调用消息接口
        ProjectDetail detail = projectDetailService.selectByPrimaryKey(projectId);
        String projectName = detail.getName();
        String tel = leadingUserProjectorService.selectById(detail.getUserId()).getTel();
        Long type = 1004L;
        Map<String,String> para= new HashMap();
        para.put("${projectName}",projectName);
        para.put("${username}",leadingUser.getUsername());
        boolean messageResult = messageManageService.allNoticeAdd(tel,type, para);
        return ResultData.setCodeAndMessage(result,"报名成功！","报名失败！");
    }

    @RequestMapping(value = "/canceljoin")
    public String cancelJoin(Model model, @RequestParam(value = "projectId") Long projectId) // 项目ID
    {
        LeadingUser leadingUser=new LeadingUser();
        leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
        int result = designerRelationshipService.deleteByUserIdAndProjectId(leadingUser.getId(),projectId); // 取消报名
        return "forward:/center/myProject";
    }
}
