package com.design.background.controller.admin;

import com.design.background.entity.DicProjectPeriod;
import com.design.background.service.DicProjectPeriodService;
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
 * 项目工期字典类
 * @author 李永成
 */

@Controller
@RequestMapping("/admin/projectperiod")
public class DicProjectPeriodController {
	
	private static final Logger logger = LoggerFactory.getLogger(DicProjectPeriodController.class);
	
	@Autowired
	private DicProjectPeriodService projectPeriodService; //操作项目工期的Service类

	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description: 添加项目工期字典信息
	* @Param: Model
	* @return: dic/add-projectPeriod
	*/
	@RequestMapping(value = "/addprojectperiod")
	public String addProjectPeriodBefore(Model model) {
		return "admin/dic/add-projectperiod";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  添加项目工期字典信息
	* @Param:  code,description
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/insertprojectperiod")
	public String insertProjectPeriod(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "description", required = false, defaultValue="") String description){
		int code1 = Integer.parseInt(code);
		try {
			DicProjectPeriod ProjectPeriod=projectPeriodService.selectProjectPeriodByDescription(description,code1);
			if(ProjectPeriod!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag = projectPeriodService.insertProjectPeriod(code1,description);
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
	* @Description:  通过输入描述信息查询项目工期字典信息
	* @Param:  description,pageNo,pageNo
	* @return:  dic/show-projectperiod
	*/
	//通过项目工期名查询项目工期
	@RequestMapping("/selectprojectperiod")
	public String selectProjectPeriod(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "description", required = false, defaultValue="") String description) {
		List<DicProjectPeriod> projectPeriodList = null;
		if (description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
			description=description.trim();
			projectPeriodList = projectPeriodService.selectProjectPeriod(description);
		}
		else {
			projectPeriodList = projectPeriodService.selectProjectPeriod();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<DicProjectPeriod> pageInfo = new PageInfo<DicProjectPeriod>(projectPeriodList);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/dic/show-projectperiod";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  通过id删除项目工期字典信息
	* @Param:  DicProjectPeriod projectPeriod
	* @return:  1
	*/
	@RequestMapping("/deleteprojectperiod/{id}")
	@ResponseBody
	public String deleteProjectPeriod(@ModelAttribute DicProjectPeriod projectPeriod){
		logger.debug("调用ProjectPeriod/deleteProjectPeriod");
		String result="0";
		boolean flag=false;
		try {
			flag=projectPeriodService.deleteById(projectPeriod.getId().intValue());
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
	* @Description:  删除选中的多个id,批量删除项目工期字典信息
	* @Param:  Long[] ids
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				projectPeriodService.deleteById(Integer.parseInt(String.valueOf(id)));
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
	* @Param:  DicProjectPeriod projectPeriod
	* @return:
	*/
	@RequestMapping(value = "/updatebyid/{id}")
	public String updateByIdBefore(Model model, @ModelAttribute(value = "projectPeriod") DicProjectPeriod projectPeriod,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		projectPeriod = projectPeriodService.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("projectPeriod", projectPeriod);
		return "admin/dic/editor-projectperiod";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  编辑完信息之后把项目工期字典信息更新到数据库
	* @Param:  DicProjectPeriod projectPeriod
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updateById(Model model, @ModelAttribute(value = "ProjectPeriod") DicProjectPeriod projectPeriod,HttpServletRequest request) {
		logger.debug("调用projectperiod/updatebyid");
		int id = projectPeriod.getId().intValue();
		DicProjectPeriod result=new DicProjectPeriod();
		try {
			projectPeriodService.deleteById(id); // 删除选中的字典信息
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(projectPeriod.getId());
			result.setDescription(request.getParameter("description1")); // 把原来的字典信息记录下来,若更新失败重新插回到数据库中
			DicProjectPeriod projectPeriod2=projectPeriodService.selectProjectPeriodByDescription(projectPeriod.getDescription(),projectPeriod.getCode());
			if(projectPeriod2==null) {
				projectPeriodService.insertProjectPeriodSelective(projectPeriod); // ProjectPeriod代表更改过后的数据，插入到数据库中
				return "1";
			}
			else {
				projectPeriodService.insertProjectPeriodSelective(result); // result是改变之前的值，插入到数据库中
				return "2";
			}
		}
		catch (Exception e) {
			projectPeriodService.insertProjectPeriodSelective(result); // result是改变之前的值，插入到数据库中
			return "2";
		}
	}
}
