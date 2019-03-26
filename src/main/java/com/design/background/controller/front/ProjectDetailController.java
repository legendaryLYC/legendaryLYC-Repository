package com.design.background.controller.front;

import com.design.background.common.controller.BaseController;
import com.design.background.config.Constant;
import com.design.background.config.FrontConfig;
import com.design.background.controller.admin.ProjectManageController;
import com.design.background.entity.DesignerRelationship;
import com.design.background.entity.LeadingUser;
import com.design.background.entity.ProjectDetail;
import com.design.background.form.ProjectForm;
import com.design.background.mapper.DicCityMapper;
import com.design.background.model.ResultData;
import com.design.background.service.DesignerRelationshipService;
import com.design.background.service.DicService;
import com.design.background.service.ProjectManageService;
import com.design.background.util.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @description: 从项目大厅点击项目详情进入详情页面
 * @author: 李永成
 * @create: 2019-02-15
 **/
@Controller
@RequestMapping("/projectdetail")
public class ProjectDetailController extends BaseController {
	@Autowired
	private ProjectManageService projectManageService;
	@Autowired
	private DesignerRelationshipService designerRelationshipService;
	@Autowired
	private HttpSession httpSession;

	private static final Logger logger = LoggerFactory.getLogger(ProjectDetailController.class);

	@RequestMapping(value = { "", "/" })
    public String projectDetail(Model model,HttpServletRequest request){
		// 如果传入的参数为空，重定向到项目大厅
		if(request.getParameter("id") == null){
			return "redirect:/hall";
		}
		model.addAttribute("nav","li2");
		// 获取用户ID
		LeadingUser leadingUser=new LeadingUser();
		leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
		Long userId = null;
		if(leadingUser != null)
		{
			userId = leadingUser.getId();
		}
        Long projectId=Long.parseLong(request.getParameter("id"));
		//根据id查询项目详情
        ProjectDetail projectDetail = new ProjectDetail();
        projectDetail.setId(projectId);
		ProjectForm project = new ProjectForm();
		project = projectManageService.selectProjectsFormById(projectDetail);
		//判断项目状态是否是已发布在建或已结项,是否是不存在的项目
		if(project == null || ( project .getProcessCode() != 1004 && project .getProcessCode() != 1005 && project .getProcessCode() != 1006 )) {
			return "error/403";
		}
		//检验用户报名情况是否完整
		Integer[] arr = designerRelationshipService.getUnselectedDesigners(projectId);
		//检验是否有带头人
		int count =  designerRelationshipService.selectCountByProjectId(projectId);
		Integer imgStatus = null;
		if ("已发布".equals(project.getStatus())){
			if(arr != null && arr.length == 0 && count == 0){
				imgStatus = 2;
			}
			else if(arr != null && arr.length == 0 && count > 0){
				imgStatus = 3;
			}
			else{
				imgStatus = 1;
			}
		}
		else if("在建".equals(project.getStatus())){
			if("方案阶段".equals(project.getStage())){
				imgStatus = 4;
			}
			else if("施工图阶段".equals(project.getStage())){
				imgStatus = 5;
			}
		}
		else if("已结项".equals(project.getStatus())){
			imgStatus = 6;
		}
		model.addAttribute("project", project);
		model.addAttribute("joinStatus", "");
		model.addAttribute("imgStatus",imgStatus);
		if(DateUtil.local2Date(LocalDate.now()).after(project.getRegistrationDeadline()))
		{
			model.addAttribute("joinStatus", "报名已截止");
		}

		if(leadingUser == null)
		{
			return "front/pro-detail";
		}
		else if(leadingUser.getRoleCode().equals(1001) && userId!=null)
		{
			DesignerRelationship designerRelationship = new DesignerRelationship();
			designerRelationship = designerRelationshipService.selectJoinStatus(userId,projectId);

			//判断报名状态
			if(designerRelationship != null)
			{
				model.addAttribute("joinStatus", "已报名此项目");
			}
		}
		else
		{
			// 设计师登陆，不显示报名按钮
			model.addAttribute("joinStatus", "项目方");
		}
		if("在建".equals(project.getStatus()))
		{
			//项目在建，不显示报名按钮
			model.addAttribute("joinStatus", "在建");
		}
		else if("已结项".equals(project.getStatus()))
		{
			//项目在建，不显示报名按钮
			model.addAttribute("joinStatus", "已结项");
		}
		
        return "front/pro-detail";
    }
	@RequestMapping(value = "/projector")
	public String projectDetailForProjector(Model model,Long id,HttpServletRequest request){
		// 如果传入的参数为空，重定向到我的项目
		if(id == null){
			return "redirect:/center/myProject";
		}
		Long projectId=Long.parseLong(request.getParameter("id"));
		// 根据id查看详情
		ProjectDetail projectDetail = new ProjectDetail();
		projectDetail.setId(id);
		ProjectForm project = new ProjectForm();
		project = projectManageService.selectProjectsFormById(projectDetail);

		//检验要查看的是否是当前项目方发布的项目
		//检验用户报名情况是否完整
		Integer[] arr = designerRelationshipService.getUnselectedDesigners(projectId);
		//检验是否有带头人
		int count =  designerRelationshipService.selectCountByProjectId(projectId);
		Integer imgStatus = null;
		if ("已发布".equals(project.getStatus())){
			if(arr != null && arr.length == 0 && count == 0){
				imgStatus = 2;
			}
			else if(arr != null && arr.length == 0 && count > 0){
				imgStatus = 3;
			}
			else{
				imgStatus = 1;
			}
		}
		else if("在建".equals(project.getStatus())){
			if("方案阶段".equals(project.getStage())){
				imgStatus = 4;
			}
			else if("施工图阶段".equals(project.getStage())){
				imgStatus = 5;
			}
		}
		else if("已结项".equals(project.getStatus())){
			imgStatus = 6;
		}
		model.addAttribute("imgStatus",imgStatus);
		// 获取用户ID
		LeadingUser leadingUser=new LeadingUser();
		leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
		Long userId = null;
		if(leadingUser != null)
		{
			userId = leadingUser.getId();
		}
		if(project != null && project.getUserId().equals(userId)) {
			model.addAttribute("project", project);
			return "front/pro-detailforprojector";
		}else {
			return "error/403";
		}
	}
	/**
	* @Author: 孟晓冬
	* @Date: 2019/2/20
	* @Description:  下载权限判断
	*/
	@ResponseBody
	@RequestMapping(value =  "/ispermission")
	public ResultData isPermission(Long projectId) {
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：font/pro-detail，传过来的参数为projectId=" + projectId);
		ResultData resultToPage = new ResultData();

		LeadingUser leadingUser=new LeadingUser();
		leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);

		Boolean result = false;
		if(leadingUser != null){
			result = projectManageService.isHavaPermission(leadingUser.getId(),projectId);
		}

		//返回结果
		if(result == true){
			resultToPage.setCode("000000");
			resultToPage.setMessage("该用户有权限！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("该用户无权限！");
		}
		return resultToPage;
	}
}
