package com.design.background.controller.admin;

import com.design.background.entity.DicProjectType;
import com.design.background.service.DicProjectTypeService;
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
 * 项目类型字典类
 * @author 李永成
 */

@Controller
@RequestMapping("/admin/projecttype")
public class DicProjectTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(DicProjectTypeController.class);
	
	@Autowired
	private DicProjectTypeService projectTypeService; //操作项目类型的Service类

	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description: 添加项目类型字典信息
	* @Param: Model
	* @return: dic/add-projecttype
	*/
	@RequestMapping(value = "/addprojecttype")
	public String addProjectTypeBefore(Model model) {
		return "admin/dic/add-projecttype";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  添加项目类型字典信息
	* @Param:  code,description
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/insertprojecttype")
	public String insertProjectType(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "description", required = false, defaultValue="") String description){
		int code1 = Integer.parseInt(code);
		try {
			DicProjectType ProjectType=projectTypeService.selectProjectTypeByDescription(description,code1);
			if(ProjectType!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag = projectTypeService.insertProjectType(code1,description);
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
	* @Description:  通过输入描述信息查询项目类型字典信息
	* @Param:  description,pageNo,pageNo
	* @return:  dic/show-projecttype
	*/
	//通过项目类型名查询项目类型
	@RequestMapping("/selectprojecttype")
	public String selectProjectType(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "description", required = false, defaultValue="") String description) {
		List<DicProjectType> projectTypeList = null;
		if (description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
			description=description.trim();
			projectTypeList = projectTypeService.selectProjectType(description);
		}
		else {
			projectTypeList = projectTypeService.selectProjectType();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<DicProjectType> pageInfo = new PageInfo<DicProjectType>(projectTypeList);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/dic/show-projecttype";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  通过id删除项目类型字典信息
	* @Param:  DicProjecttype projecttype
	* @return:  1
	*/
	@RequestMapping("/deleteprojecttype/{id}")
	@ResponseBody
	public String deleteProjecttype(@ModelAttribute DicProjectType projecttype){
		logger.debug("调用Projecttype/deleteProjecttype");
		String result="0";
		boolean flag=false;
		try {
			flag=projectTypeService.deleteById(projecttype.getId().intValue());
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
	* @Description:  删除选中的多个id,批量删除项目类型字典信息
	* @Param:  Long[] ids
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				projectTypeService.deleteById(Integer.parseInt(String.valueOf(id)));
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
	* @Param:  DicProjecttype projecttype
	* @return:
	*/
	@RequestMapping(value = "/updatebyid/{id}")
	public String updateByIdBefore(Model model, @ModelAttribute(value = "projecttype") DicProjectType projectType,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		projectType = projectTypeService.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("projectType", projectType);
		return "admin/dic/editor-projecttype";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  编辑完信息之后把项目类型字典信息更新到数据库
	* @Param:  DicProjecttype projecttype
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updateById(Model model, @ModelAttribute(value = "Projecttype") DicProjectType projectType,HttpServletRequest request) {
		logger.debug("调用Projecttype/updatebyid");
		int id = projectType.getId().intValue();
		DicProjectType result=new DicProjectType();
		try {
			projectTypeService.deleteById(id); // 删除选中的字典信息
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(projectType.getId());
			result.setDescription(request.getParameter("description1")); // 把原来的字典信息记录下来,若更新失败重新插回到数据库中
			DicProjectType projecttype2=projectTypeService.selectProjectTypeByDescription(projectType.getDescription(),projectType.getCode());
			if(projecttype2==null) {
				projectTypeService.insertProjectTypeSelective(projectType); // Projecttype代表更改过后的数据，插入到数据库中
				return "1";
			}
			else {
				projectTypeService.insertProjectTypeSelective(result); // result是改变之前的值，插入到数据库中
				return "2";
			}
		}
		catch (Exception e) {
			projectTypeService.insertProjectTypeSelective(result); // result是改变之前的值，插入到数据库中
			return "2";
		}
	}
}
