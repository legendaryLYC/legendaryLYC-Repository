package com.design.background.controller.admin;

import com.design.background.entity.DicProjectScale;
import com.design.background.entity.DicProjectStage;
import com.design.background.entity.ProjectDetail;
import com.design.background.form.ProjectForm;
import com.design.background.model.ResultData;
import com.design.background.service.DicService;
import com.design.background.service.LeadingUserProjectorService;
import com.design.background.service.MessageManageService;
import com.design.background.service.ProjectManageService;
import com.design.background.util.StringToDate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author： 孟晓冬
 * @Date： 2019/2/13
 * @Description： 这是后台项目审核的增删改查controller
 */
@Controller
@RequestMapping("/admin/projectexamine")
public class ProjectExamineController {

	@Autowired
	private ProjectManageService projectManageService; // 调用晓东接口，在前台筛选出状态为待审核的项目
	@Autowired
	private DicService dicService;
	@Autowired
	private MessageManageService messageManageService;
	@Autowired
	LeadingUserProjectorService leadingUserProjectorService;

	private static final Logger logger = LoggerFactory.getLogger(ProjectExamineController.class);
    /**
     * @Description: 返回项目列表页的controller
     * @return: java.lang.String
     * @Author: 孟晓冬
     * @Date: 2019/2/13
     */
	@RequestMapping(value = { "", "/" })
    public String projectList(Model model,ProjectDetail projectDetail,
    		@RequestParam(value = "timeFrom", required = false) String timeFrom,
    		@RequestParam(value = "timeTo", required = false) String timeTo,
    		@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
    		@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
		    @RequestParam(value = "type", required = false, defaultValue = "0") String type)
    {
		//日期字符转化为Date型,  发布日期筛选范围
		java.util.Date dateFrom = StringToDate.toUtilDate(timeFrom);
		java.util.Date dateTo = StringToDate.toUtilDate(timeTo);
		//判断审核类型
		if("1".equals(type)) {
			projectDetail.setCompleteAudting(1);
		}else {
			projectDetail.setProcessCode(1001);
		}
		
		//分页
		PageHelper.startPage(pageNo, pageSize);
		List<ProjectForm> result = projectManageService.selectProjectsSelective(projectDetail, dateFrom, dateTo);
		PageInfo<ProjectForm> pageInfo = new PageInfo<ProjectForm>(result);
		if (pageNo > pageInfo.getPages()) {
			pageNo = pageInfo.getPages();
			PageHelper.startPage(pageNo, pageSize);
			result = projectManageService.selectProjectsSelective(projectDetail, dateFrom, dateTo);
			pageInfo = new PageInfo<ProjectForm>(result);
		}
		model.addAttribute("pageInfo", pageInfo);
		//回显查询条件
		model.addAttribute("project",projectDetail);
        model.addAttribute("type",type);
        model.addAttribute("timeFrom", dateFrom);
		model.addAttribute("timeTo", dateTo);
        
		//获取项目规模和项目状态字典
		List<DicProjectScale> models = dicService.getModels();
		List<DicProjectStage> stages = dicService.getStages();
		model.addAttribute("models",models);
		model.addAttribute("stages",stages);
        return "admin/projectExamine/projectExamine";
    }
	/**
	* @Author: 孟晓冬
	* @Date: 2019/2/15
	* @Description:  通过审核
	* @Param:  id
	* @return:  json对象:resultToPage
	*/
	@ResponseBody
	@RequestMapping(value =  "/throughapplication", produces = "application/json;charset=utf-8")
	public ResultData throughApplication(@RequestParam(value = "id", required = true) Long id) {
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：projectexamine/throughapplication，传过来的参数为id=" + id );
		ResultData resultToPage = new ResultData();
		String examineType = "";
		//查出该记录判断属于待审核状态还是审核验收状态
		ProjectDetail detail=projectManageService.selectByPrimaryKey(id);
		boolean flag=false;
		if(detail.getProcessCode()==1001){
			detail.setProcessCode(1002);
			detail.setProcessTime(new Date(System.currentTimeMillis()));
			examineType = "待审核";
		}
		else if(detail.getCompleteAudting()==1){
			detail.setProcessCode(1006);
			detail.setCompleteAudting(0);
			examineType = "结项审核";
		}
		// 调用消息接口
		String projectName = detail.getName();
		String tel = leadingUserProjectorService.selectById(detail.getUserId()).getTel();
		Long type = 1013L;
		Map<String,String> para= new HashMap();
		para.put("${projectName}",projectName);
		para.put("${examineType}",examineType);
		boolean messageResult = messageManageService.allNoticeAdd(tel,type, para);
		// 调用接口结束
		flag = projectManageService.updateById(detail);
		//返回结果
		if(flag == true){
			resultToPage.setCode("000000");
			resultToPage.setMessage("通过成功！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("通过失败！");
		}
		return resultToPage;
	}
	
	/**
	 * 通过所有勾选的项目
	 * @author 孟晓冬
	 */
	@ResponseBody
	@RequestMapping(value =  "/passallprojects", produces = "application/json;charset=utf-8")
	public ResultData passAllProjects(@RequestBody Long[] ids) {
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：admin/projectExamine/projectManage，传过来的参数为ids=" + ids );
		ResultData resultToPage = new ResultData();
		
		//记录通过结果
		Boolean result=false;
		for (Long id : ids) {
			//通过该id的项目
			//查出该记录判断属于待审核状态还是审核验收状态
			ProjectDetail detail=projectManageService.selectByPrimaryKey(id);
			if(detail.getProcessCode()==1001){
				detail.setProcessCode(1002);
				detail.setProcessTime(new Date(System.currentTimeMillis()));
			}
			else if(detail.getCompleteAudting()==1){
				detail.setProcessCode(1006);
				detail.setCompleteAudting(0);
			}
			result = projectManageService.updateById(detail);
		}
		//返回结果
		if(result == true){
			resultToPage.setCode("000000");
			resultToPage.setMessage("通过成功！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("通过失败！");
		}
		return resultToPage;
	}
	
	/**
	 * @Author: 孟晓冬
	 * @Date: 2019/2/15
	 * @Description:  审核拒绝
	 * @Param:  id
	 * @return:  拒绝的页面，包含输入框，填写拒绝理由
	 */
	@RequestMapping(value = "/rejectapplication/{id}")
	public String rejectApplicationBefore(Model model, @PathVariable("id") Long id,
			@ModelAttribute(value = "detail")ProjectDetail detail) {
		// 根据ID项目详情，在前台显示出来
		detail=projectManageService.selectByPrimaryKey(id);
		model.addAttribute("ProjectDetail", detail);
		return "admin/projectExamine/editor-project";
	}
	
	/**
	 * @Author: 李永成
	 * @Date: 2019/2/13
	 * @Description:  更改项目的状态，调用消息接口，发送审核结果的消息。
	 * @Param:  DicProjectFee projectFee
	 * @return:  1
	 */
	@ResponseBody
	@RequestMapping(value = "/rejectapplication")
	public String rejectApplication(Model model, @ModelAttribute(value = "detail") ProjectDetail detail, HttpServletRequest request,
							 @RequestParam(value = "reason", required = false ) String reason) {
		logger.debug("projectexamine/rejectapplication");
		boolean flag = false ;
		try {
				// 得到前台传过来的id.
				Long id=detail.getId();
				// 这里得到前台的审核拒绝理由信息，调用消息通知接口发送给项目方审核失败的原因。
				logger.error(reason);
				String examineType = "";
				detail=projectManageService.selectByPrimaryKey(id);
				if(detail.getProcessCode()==1001){
					detail.setProcessTime(new Date(System.currentTimeMillis()));
					detail.setProcessCode(1003);
					examineType="待审核"; // 确定项目审核的类型，发送消息
				}
				else if(detail.getCompleteAudting()==1){
					detail.setCompleteAudting(0);
					examineType="结项审核"; // 确定项目审核的类型，发送消息
				}
				flag = projectManageService.updateById(detail);
				// 调用消息接口
				String projectName = detail.getName();
				String tel = leadingUserProjectorService.selectById(detail.getUserId()).getTel();
				Long type = 1012L;
				Map<String,String> para= new HashMap();
				para.put("${projectName}",projectName);
				para.put("${message}",reason);
				para.put("${examineType}",examineType);
				boolean messageResult = messageManageService.allNoticeAdd(tel,type, para);
				// 调用接口结束
				//返回结果
				if(flag == true){
					return "1";
				}
				else{
					return "2";
				}
			}
		catch (Exception e) {
			logger.error("审核出现异常");
			return "2";
		}
	}
}