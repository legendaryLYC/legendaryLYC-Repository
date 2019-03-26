package com.design.background.controller.admin;

import com.design.background.entity.DicProjectStage;
import com.design.background.service.DicProjectStageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 项目组建分类字典类
 * @author 李永成
 */

@Controller
@RequestMapping("/admin/projectstage")
public class DicProjectStageController {

	private static final Logger logger = LoggerFactory.getLogger(DicProjectStageController.class);

	@Autowired
	private DicProjectStageService projectStageService; //操作项目组建分类的Service类

	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description: 添加项目组建分类字典信息
	* @Param: Model
	* @return: dic/add-projectstage
	*/
	@RequestMapping(value = "/addprojectstage")
	public String addProjectStageBefore(Model model) {
		return "admin/dic/add-projectstage";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  添加项目组建分类字典信息
	* @Param:  code,description
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/insertprojectstage")
	public String insertProjectStage(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "description", required = false, defaultValue="") String description){
		int code1 = Integer.parseInt(code);
		try {
			DicProjectStage ProjectStage=projectStageService.selectProjectStageByDescription(description,code1);
			if(ProjectStage!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag = projectStageService.insertProjectStage(code1,description);
				if(flag == true) {
					return "1"; //插入成功
				}
			}
		}
		catch (Exception e) {
			return "0"; // 根据两个字段查出了两条数据。
		}
		return "0";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  通过输入描述信息查询项目组建分类字典信息
	* @Param:  description,pageNo,pageNo
	* @return:  dic/show-projectstage
	*/
	//通过项目组建分类名查询项目组建分类
	@RequestMapping("/selectprojectstage")
	public String selectProjectStage(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "description", required = false, defaultValue="") String description) {
		List<DicProjectStage> projectStageList = null;
		if (description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
			description=description.trim();
			projectStageList = projectStageService.selectProjectStage(description);
		}
		else {
			projectStageList = projectStageService.selectProjectStage();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<DicProjectStage> pageInfo = new PageInfo<DicProjectStage>(projectStageList);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/dic/show-projectstage";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  通过id删除项目组建分类字典信息
	* @Param:  DicProjectStage projectStage
	* @return:  1
	*/
	@RequestMapping("/deleteprojectstage/{id}")
	@ResponseBody
	public String deleteProjectStage(@ModelAttribute DicProjectStage projectStage){
		logger.debug("调用projectstage/deleteprojectstage");
		String result="0";
		boolean flag=false;
		try {
			flag=projectStageService.deleteById(projectStage.getId().intValue());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(flag=true) {
			result="1";
		}
		return result;
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  删除选中的多个id,批量删除项目组建分类字典信息
	* @Param:  Long[] ids
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				projectStageService.deleteById(Integer.parseInt(String.valueOf(id)));
			}
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  根据选中的id查到该字典信息，在前台回显，编辑
	* @Param:  DicProjectStage projectStage
	* @return:
	*/
	@RequestMapping(value = "/updatebyid/{id}")
	public String updateByIdBefore(Model model, @ModelAttribute(value = "projectStage") DicProjectStage projectStage,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		projectStage = projectStageService.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("projectStage", projectStage);
		return "admin/dic/editor-projectstage";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  编辑完信息之后把项目组建分类字典信息更新到数据库
	* @Param:  DicProjectStage projectStage
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updateById(Model model, @ModelAttribute(value = "ProjectStage") DicProjectStage projectStage,HttpServletRequest request) {
		logger.debug("调用projectStage/updatebyid");
		int id = projectStage.getId().intValue();
		DicProjectStage result=new DicProjectStage();
		try {
			projectStageService.deleteById(id); // 删除选中的字典信息
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(projectStage.getId());
			result.setDescription(request.getParameter("description1")); // 把原来的字典信息记录下来,若更新失败重新插回到数据库中
			DicProjectStage projectStage2=projectStageService.selectProjectStageByDescription(projectStage.getDescription(),projectStage.getCode());
			if(projectStage2==null) {
				projectStageService.insertProjectStageSelective(projectStage); // ProjectStage代表更改过后的数据，插入到数据库中
				return "1";
			}
			else {
				projectStageService.insertProjectStageSelective(result); // result是改变之前的值，插入到数据库中
				return "2";
			}
		}
		catch (Exception e) {
			projectStageService.insertProjectStageSelective(result); // result是改变之前的值，插入到数据库中
			return "2";
		}
	}
}
