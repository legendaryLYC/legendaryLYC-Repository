package com.design.background.controller.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.design.background.entity.DicProjectStage;
import com.design.background.entity.DicReviewStatus;
import com.design.background.form.ProjectPhaseAuditForm;
import com.design.background.service.DicService;
import com.design.background.service.ProjectPhaseAuditService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Author： 孟晓冬
 * @Date： 2019/2/20
 * @Description： 这是后台项目阶段管理相关功能controller
 */
@Controller
@RequestMapping("/admin/phaseaudit")
public class PhaseAuditController {
	
	@Autowired
	private DicService dicService;
	@Autowired
	private ProjectPhaseAuditService projectPhaseAuditService;
	
	/**
     * @Description: 返回项目列表页的controller
     * @return: java.lang.String
     * @Author: 孟晓冬
     * @Date: 2019/2/13
     */
	@RequestMapping(value = { "", "/" })
    public String phaseAuditList(Model model,ProjectPhaseAuditForm projectPhaseAuditForm,
    		@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
    		@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize)
    {
		//分页
		PageHelper.startPage(pageNo, pageSize);
		List<ProjectPhaseAuditForm> result = projectPhaseAuditService.getPhaseAuditsSelective(projectPhaseAuditForm);
		PageInfo<ProjectPhaseAuditForm> pageInfo = new PageInfo<ProjectPhaseAuditForm>(result);
		if (pageNo > pageInfo.getPages()) {
			pageNo = pageInfo.getPages();
			PageHelper.startPage(pageNo, pageSize);
			result = projectPhaseAuditService.getPhaseAuditsSelective(projectPhaseAuditForm);
			pageInfo = new PageInfo<ProjectPhaseAuditForm>(result);
		}
		model.addAttribute("pageInfo", pageInfo);
		
		//回显查询条件
		model.addAttribute("audit", projectPhaseAuditForm);
		
		//获取项目规模和项目状态字典
		List<DicProjectStage> stages = dicService.getStages();
		List<DicReviewStatus> reviewStatus = dicService.getReviewStatus();
		
		model.addAttribute("stages", stages);
		model.addAttribute("reviewStatus", reviewStatus);
        return "admin/projectManage/phaseAuditList";
    }
}
