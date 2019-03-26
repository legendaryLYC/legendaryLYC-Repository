package com.design.background.controller.front;

import com.design.background.common.controller.BaseController;
import com.design.background.config.Constant;
import com.design.background.config.FrontConfig;
import com.design.background.entity.*;
import com.design.background.form.ProjectPhaseAuditForm;
import com.design.background.model.ResultData;
import com.design.background.service.DesignerRelationshipService;
import com.design.background.service.MessageGetService;
import com.design.background.service.PhaseAuditFilesService;
import com.design.background.service.ProjectPhaseAuditService;
import com.design.background.util.DesignFileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Author： 李紫林
 * @Date： 2019/2/20
 * @Description： 前台项目阶段controller
 */

@Controller
@RequestMapping("/center/frontstage")
public class StageController extends BaseController {

    @Autowired
    private ProjectPhaseAuditService projectPhaseAuditService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private PhaseAuditFilesService phaseAuditFilesService;
    @Autowired
    private DesignerRelationshipService designerRelationshipService;
    @Autowired
    private MessageGetService messageGetService;

    private static final Logger logger = LoggerFactory.getLogger(StageController.class);

    /**
     * @Description: 展示项目阶段信息的controller
     * @Param: [model, projectId]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/2/21
     */
    @RequestMapping("/showstage")
    public String showStage(Model model, @RequestParam(value = "projectid")Long projectId)
    {
        // 展示此项目的阶段
        ProjectPhaseAuditForm projectPhaseAuditForm = projectPhaseAuditService.showStage(projectId);
        model.addAttribute("project",projectPhaseAuditForm);
        model.addAttribute("projectId",projectId);
        LeadingUser leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
        String acceptUser = leadingUser.getTel();
        List<NotificationManage> messagelist = messageGetService.selectByacceptUser(acceptUser,leadingUser.getCreateTime());
        //查找已读消息
        List<NotificationView> readedlist = messageGetService.selectreadedMessage(leadingUser.getId(),leadingUser.getCreateTime());
        model.addAttribute("noRead",messagelist.size()-readedlist.size());
        return "front/start_check_stage";
    }



    /**
     * @Description: 发起阶段验收的cotroller
     * @Param: [model, projectId, files, stageCode]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/2/21
     */
    @RequestMapping("/startstagecheck")
    public String startStageCheck(Model model,
                                  @RequestParam("projectid")Long projectId,
                                  @RequestParam("files") MultipartFile[] files,
                                  @RequestParam("stageCode") Integer stageCode)
    {
        LeadingUser leadingUser = new LeadingUser();
        leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
        Long id = projectPhaseAuditService.startStageCheckOne(new ProjectPhaseAudit(projectId, stageCode, Constant.UNCHECK, new Date(), null));

        for (MultipartFile file : files)
        {
            PhaseAuditFiles phaseAuditFiles = new PhaseAuditFiles(null, null, leadingUser.getId(), null);
            if (StringUtils.isNotBlank(file.getOriginalFilename()))
            {
                //上传到阿里云
                String filePath = DesignFileUtils.uploadSingleFile(file);
                phaseAuditFiles.setFileName(file.getOriginalFilename());
                phaseAuditFiles.setFilePath(filePath);
                phaseAuditFiles.setPhaseAuditId(id);
                if (StringUtils.isNoneBlank(filePath)) {
                    projectPhaseAuditService.startStageCheckTwo(phaseAuditFiles);
                }
            }
        }
        return "redirect:/center/myProject";
    }




    /**
     * @Description: 加载编辑阶段验收的回显数据cotroller
     * @Param: [model, projectId, files, stageCode]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/2/21
     */
    @RequestMapping("/toeditstagecheck")
    public String toEditStageCheck(Model model, @RequestParam("projectid")Long projectId,@RequestParam("stageCode")Integer stageCode)
    {

        LeadingUser leadingUser = new LeadingUser();
        leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
        List<PhaseAuditFiles> phaseAuditFilesList = phaseAuditFilesService.selectByProjectIdAndDesignerId(projectId,leadingUser.getId(),stageCode);

        List<ProjectPhaseAudit> projectPhaseAudits = projectPhaseAuditService.selectByProjectIdAndStageCode(projectId,stageCode);
        model.addAttribute("projectPhaseAudit",projectPhaseAudits.get(0));
        model.addAttribute("phaseAuditFilesList",phaseAuditFilesList);


        return "front/edit_check_stage";
    }

    /**
     * @Description: 删除阶段验收文件
     * @Param: [id]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/2/21
     */
    @ResponseBody
    @RequestMapping("/deleteFile")
    public String deleteFile(@RequestParam(value="id",required=true) Long id) {
        int stat = phaseAuditFilesService.deleteByPrimaryKey(id);
        if(stat == 1) {
            return "success";
        }
        else {
            return "fail";
        }
    }



    /**
     * @Description: 修改阶段验收的cotroller
     * @Param: [model, projectId, files, stageCode]
     * @return: java.lang.String
     * @Author: 李紫林
     * @Date: 2019/2/21
     */
    @RequestMapping("/editstagecheck")
    public String editStageCheck(Model model,
                                  @RequestParam("files") MultipartFile[] files,
                                 @RequestParam("stageCode") Integer stageCode,
                                 @RequestParam("phaseauditid")Long phaseAuditId,
                                 @RequestParam("projectid") Long projectId)
    {
        LeadingUser leadingUser = new LeadingUser();
        leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
        ProjectPhaseAudit projectPhaseAudit = new ProjectPhaseAudit(phaseAuditId,projectId,stageCode,1000,new Date(), null);
        projectPhaseAuditService.updateByPrimaryKeySelective(projectPhaseAudit);

        for (MultipartFile file : files)
        {
            PhaseAuditFiles phaseAuditFiles = new PhaseAuditFiles(null, null, leadingUser.getId(), phaseAuditId);
            if (StringUtils.isNotBlank(file.getOriginalFilename()))
            {
                //上传到阿里云
                String filePath = DesignFileUtils.uploadSingleFile(file);
                phaseAuditFiles.setFileName(file.getOriginalFilename());
                phaseAuditFiles.setFilePath(filePath);
                if (StringUtils.isNoneBlank(filePath)) {
                    phaseAuditFilesService.insertSelective(phaseAuditFiles);
                }
            }
        }
        return "redirect:/center/myProject";
    }

    /**
    * @Author: 李永成
    * @Date: 2019/2/21
    * @Description: 前台项目方阶段审核,显示项目审核的页面
    * @Param:  项目id
    * @return:  设计师信息 设计师上传的文件
    */
    @RequestMapping("/stageexamine")
    public String stageExamine(Model model,
           @RequestParam(value = "projectId", required = false) Long projectId){
        if(projectId == null){
            return "redirect:/center/myProject";
        }
        ProjectPhaseAuditForm projectStageExamine = projectPhaseAuditService.getPhaseAuditByProjectId(projectId);
        model.addAttribute("projectStageExamine",projectStageExamine); // 实体类包括设计师的信息，上传的文件信息，项目id，项目名等
        return "front/pro-stageexamine";
    }

    /**
     * @Author: 李永成
     * @Date: 2019/2/21
     * @Description: 前台项目方阶段审核,通过审核
     * @Param:  项目id
     * @return:  resultToPage resultToPage
     */
    @ResponseBody
    @RequestMapping(value =  "/throughapplication")
    public ResultData throughApplication(@RequestParam(value = "id", required = true) Long id) {
        logger.info("调用者为：projectexamine/throughapplication，传过来的参数为id=" + id );
        ResultData resultToPage = new ResultData();
        boolean flag = false;
        try {
            flag = projectPhaseAuditService.projectorThroughApplication(id);
        }
        catch (Exception e){
            flag = false;
        }
        if(flag == true){
            resultToPage.setCode("000000");
        }
        else  {
            resultToPage.setCode("999999");
        }
        return resultToPage;
    }

    /**
     * @Author: 李永成
     * @Date: 2019/2/21
     * @Description: 前台项目方阶段审核,拒绝审核页面
     * @Param:  项目id
     * @return:  填写拒绝信息的页面
     */
    @RequestMapping(value = "/rejectapplication/{id}")
    public String rejectApplicationBefore(Model model, @PathVariable("id") Long id){
        // 根据ID项目详情，在前台显示出来
        model.addAttribute("id",id);
        return "front/pro-stageexamine-rejection";

    }

    /**
     * @Author: 李永成
     * @Date: 2019/2/13
     * @Description:  前台项目方阶段审核,拒绝审核
     * @Param:  projectId reason
     * @return:  1
     */
    @ResponseBody
    @RequestMapping(value = "/rejectapplication")
    public String rejectApplication(Model model,HttpServletRequest request,
                                    @RequestParam(value = "reason", required = false ) String reason,
                                    @RequestParam(value = "id", required = false ) Long id) {
        logger.debug("/center/frontstage/rejectapplication");
        boolean flag = false ;
        flag = projectPhaseAuditService.projectorRejectApplication(id);
        designerRelationshipService.examineProject(id,reason);
        if(flag == true){
            return "1";
        }
        else {
            return "2";
        }
    }
}
