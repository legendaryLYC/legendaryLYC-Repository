package com.design.background.controller.admin;

import com.design.background.entity.DicProjectFee;
import com.design.background.service.DicProjectFeeService;
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
 * 项目费用字典类
 * @author 李永成
 */

@Controller
@RequestMapping("/admin/projectfee")
public class DicProjectFeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(DicProjectFeeController.class);
	
	@Autowired
	private DicProjectFeeService projectFeeService; //操作项目费用的Service类

	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description: 添加项目费用字典信息
	* @Param: Model
	* @return: admin/dic/add-projectfee
	*/
	@RequestMapping(value = "/addprojectfee")
	public String addProjectFeeBefore(Model model) {
		return "admin/dic/add-projectfee";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  添加项目费用字典信息
	* @Param:  code,description
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/insertprojectfee")
	public String insertProjectFee(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "description", required = false, defaultValue="") String description){
		int code1 = Integer.parseInt(code);
		try {
			DicProjectFee ProjectFee=projectFeeService.selectProjectFeeByDescription(description,code1);
			if(ProjectFee!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag = projectFeeService.insertProjectFee(code1,description);
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
	* @Description:  通过输入描述信息查询项目费用字典信息
	* @Param:  description,pageNo,pageNo
	* @return:  admin/dic/show-projectfee
	*/
	//通过项目费用名查询项目费用
	@RequestMapping("/selectprojectfee")
	public String selectProjectFee(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "description", required = false, defaultValue="") String description) {
		List<DicProjectFee> projectFeeList = null;
		if (description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
			description=description.trim();
			projectFeeList = projectFeeService.selectProjectFee(description);
		}
		else {
			projectFeeList = projectFeeService.selectProjectFee();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<DicProjectFee> pageInfo = new PageInfo<DicProjectFee>(projectFeeList);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/dic/show-projectfee";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  通过id删除项目费用字典信息
	* @Param:  DicProjectFee projectFee
	* @return:  1
	*/
	@RequestMapping("/deleteprojectfee/{id}")
	@ResponseBody
	public String deleteProjectFee(@ModelAttribute DicProjectFee projectFee){
		logger.debug("调用ProjectFee/deleteProjectFee");
		String result="0";
		boolean flag=false;
		try {
			flag=projectFeeService.deleteById(projectFee.getId().intValue());
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
	* @Description:  删除选中的多个id,批量删除项目费用字典信息
	* @Param:  Long[] ids
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				projectFeeService.deleteById(Integer.parseInt(String.valueOf(id)));
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
	* @Param:  DicProjectFee projectFee
	* @return:
	*/
	@RequestMapping(value = "/updatebyid/{id}")
	public String updateByIdBefore(Model model, @ModelAttribute(value = "projectFee") DicProjectFee projectFee,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		projectFee = projectFeeService.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("projectFee", projectFee);
		return "admin/dic/editor-projectFee";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  编辑完信息之后把项目费用字典信息更新到数据库
	* @Param:  DicProjectFee projectFee
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updateById(Model model, @ModelAttribute(value = "ProjectFee") DicProjectFee projectFee,HttpServletRequest request) {
		logger.debug("调用ProjectFee/updatebyid");
		int id = projectFee.getId().intValue();
		DicProjectFee result=new DicProjectFee();
		try {
			projectFeeService.deleteById(id); // 删除选中的字典信息
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(projectFee.getId());
			result.setDescription(request.getParameter("description1")); // 把原来的字典信息记录下来,若更新失败重新插回到数据库中
			DicProjectFee projectFee2=projectFeeService.selectProjectFeeByDescription(projectFee.getDescription(),projectFee.getCode());
			if(projectFee2==null) {
				projectFeeService.insertProjectFeeSelective(projectFee); // ProjectFee代表更改过后的数据，插入到数据库中
				return "1";
			}
			else {
				projectFeeService.insertProjectFeeSelective(result); // result是改变之前的值，插入到数据库中
				return "2";
			}
		}
		catch (Exception e) {
			projectFeeService.insertProjectFeeSelective(result); // result是改变之前的值，插入到数据库中
			return "2";
		}
	}
}
