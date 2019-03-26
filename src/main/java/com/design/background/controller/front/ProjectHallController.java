package com.design.background.controller.front;

import java.util.List;

import com.design.background.entity.DicCity;
import com.design.background.model.ScreeningConditions;
import com.design.background.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.design.background.common.controller.BaseController;
import com.design.background.entity.ProjectDetail;
import com.design.background.form.BasicProjectForm;
import com.design.background.service.ProjectManageService;

/**
 * @description: 前台首页点击项目大厅返回页面
 * @author: 孟晓冬
 * @create: 2019-02-15
 **/
@Controller
@RequestMapping("/hall")
public class ProjectHallController extends BaseController{
	@Autowired
	private ProjectManageService projectManageService;
	@Autowired
	private DicService dicService;

	@RequestMapping(value = { "", "/" })
    public String welcome(Model model,ProjectDetail projectDetail,ScreeningConditions screeningConditions) {
		List<BasicProjectForm> result = null;
		//分页
		startPage();
		//默认显示 已发布 在建 已结项状态项目
		if(projectDetail.getProcessCode() == null) {
			result = projectManageService.selectBasicProjectsSelective(
					projectDetail, screeningConditions, 1004, 1005, 1006);
		}else {
			result = projectManageService.selectBasicProjectsSelective(
					projectDetail, screeningConditions);
		}
		//项目列表
		model.addAttribute("pageInfo",getPageInfo(result));
		//加载字典表
	    model.addAttribute("models",dicService.getModels());
        model.addAttribute("types",dicService.getTypes());
        model.addAttribute("fees",dicService.getFees());
        model.addAttribute("periods",dicService.getPeriods());
        model.addAttribute("stages",dicService.getStages());
        model.addAttribute("components",dicService.getComponents());
        model.addAttribute("statuses",dicService.getProcess());
        //model.addAttribute("cities",dicCityMapper.selectAllCity());
        //筛选条件回显
        String city = "";
        if(screeningConditions.getCityId() != null) {
			DicCity dicCity = dicService.selectCityById(screeningConditions.getCityId().toString());
			if(dicCity != null){
				city = dicCity.getName();
			}
        }
        screeningConditions.setCityDescription(city);
        model.addAttribute("screeningConditions", screeningConditions);
        model.addAttribute("projectDetail", projectDetail);
        model.addAttribute("nav","li2");
        return "front/case";
    }

	/**
	 * ajax返回城市字典
	 * @author 孟晓冬
	 */
	@ResponseBody
	@RequestMapping(value =  "/getareadic", produces = "application/json;charset=utf-8",method ={RequestMethod.POST})
	public Object[] getAreaDic() {

		return dicService.getCitys().toArray();
	}
}
