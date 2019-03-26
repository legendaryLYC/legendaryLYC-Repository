package com.design.background.controller.admin;

import com.design.background.entity.DicProjectComponent;
import com.design.background.service.DicProjectComponentService;
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
@RequestMapping("/admin/projectcomponent")
public class DicProjectComponentController {
	
	private static final Logger logger = LoggerFactory.getLogger(DicProjectComponentController.class);
	
	@Autowired
	private DicProjectComponentService projectComponentService; //操作项目组建分类的Service类

	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description: 添加项目组建分类字典信息
	* @Param: Model
	* @return: dic/add-projectComponent
	*/
	@RequestMapping(value = "/addprojectcomponent")
	public String addProjectComponentBefore(Model model) {
		return "admin/dic/add-projectcomponent";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  添加项目组建分类字典信息
	* @Param:  code,description
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/insertprojectcomponent")
	public String insertProjectComponent(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "description", required = false, defaultValue="") String description){
		int code1 = Integer.parseInt(code);
		try {
			DicProjectComponent ProjectComponent=projectComponentService.selectProjectComponentByDescription(description,code1);
			if(ProjectComponent!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag = projectComponentService.insertProjectComponent(code1,description);
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
	* @return:  admin/dic/show-projectComponent
	*/
	//通过项目组建分类名查询项目组建分类
	@RequestMapping("/selectprojectcomponent")
	public String selectProjectComponent(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "description", required = false, defaultValue="") String description) {
		List<DicProjectComponent> projectComponentList = null;
		if (description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
			description=description.trim();
			projectComponentList = projectComponentService.selectProjectComponent(description);
		}
		else {
			projectComponentList = projectComponentService.selectProjectComponent();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<DicProjectComponent> pageInfo = new PageInfo<DicProjectComponent>(projectComponentList);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/dic/show-projectcomponent";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  通过id删除项目组建分类字典信息
	* @Param:  DicProjectComponent projectcomponent
	* @return:  1
	*/
	@RequestMapping("/deleteprojectcomponent/{id}")
	@ResponseBody
	public String deleteProjectComponent(@ModelAttribute DicProjectComponent projectComponent){
		logger.debug("调用projectcomponent/deleteProjectcomponent");
		String result="0";
		boolean flag=false;
		try {
			flag=projectComponentService.deleteById(projectComponent.getId().intValue());
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
				projectComponentService.deleteById(Integer.parseInt(String.valueOf(id)));
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
	* @Param:  DicProjectComponent projectComponent
	* @return:
	*/
	@RequestMapping(value = "/updatebyid/{id}")
	public String updateByIdBefore(Model model, @ModelAttribute(value = "projectcomponent") DicProjectComponent projectComponent,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		projectComponent = projectComponentService.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("projectComponent", projectComponent);
		return "admin/dic/editor-projectcomponent";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  编辑完信息之后把项目组建分类字典信息更新到数据库
	* @Param:  DicProjectComponent projectComponent
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updateById(Model model, @ModelAttribute(value = "projectComponent") DicProjectComponent projectComponent,HttpServletRequest request) {
		logger.debug("调用projectcomponent/updatebyid");
		int id = projectComponent.getId().intValue();
		DicProjectComponent result=new DicProjectComponent();
		try {
			projectComponentService.deleteById(id); // 删除选中的字典信息
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(projectComponent.getId());
			result.setDescription(request.getParameter("description1")); // 把原来的字典信息记录下来,若更新失败重新插回到数据库中
			DicProjectComponent projectComponent2=projectComponentService.selectProjectComponentByDescription(projectComponent.getDescription(),projectComponent.getCode());
			if(projectComponent2==null) {
				projectComponentService.insertProjectComponentSelective(projectComponent); // ProjectComponent代表更改过后的数据，插入到数据库中
				return "1";
			}
			else {
				projectComponentService.insertProjectComponentSelective(result); // result是改变之前的值，插入到数据库中
				return "2";
			}
		}
		catch (Exception e) {
			projectComponentService.insertProjectComponentSelective(result); // result是改变之前的值，插入到数据库中
			return "2";
		}
	}
}
