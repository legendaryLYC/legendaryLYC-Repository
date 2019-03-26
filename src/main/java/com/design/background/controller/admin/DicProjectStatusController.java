package com.design.background.controller.admin;

import com.design.background.entity.DicProjectStatus;
import com.design.background.service.DicProjectStatusService;
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
 * 项目状态字典类
 * @author 李永成
 */

@Controller
@RequestMapping("/admin/projectstatus")
public class DicProjectStatusController {
	
	private static final Logger logger = LoggerFactory.getLogger(DicProjectStatusController.class);
	
	@Autowired
	private DicProjectStatusService projectStatusService; //操作项目状态的Service类

	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description: 添加项目状态字典信息
	* @Param: Model
	* @return: dic/add-projectStatus
	*/
	@RequestMapping(value = "/addprojectstatus")
	public String addProjectStatusBefore(Model model) {
		return "admin/dic/add-projectStatus";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  添加项目状态字典信息
	* @Param:  code,description
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/insertprojectstatus")
	public String insertProjectStatus(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "description", required = false, defaultValue="") String description){
		int code1 = Integer.parseInt(code);
		try {
			DicProjectStatus ProjectStatus=projectStatusService.selectProjectStatusByDescription(description,code1);
			if(ProjectStatus!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag = projectStatusService.insertProjectStatus(code1,description);
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
	* @Description:  通过输入描述信息查询项目状态字典信息
	* @Param:  description,pageNo,pageNo
	* @return:  dic/show-projectstatus
	*/
	//通过项目状态名查询项目状态
	@RequestMapping("/selectprojectstatus")
	public String selectProjectStatus(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "description", required = false, defaultValue="") String description) {
		List<DicProjectStatus> projectStatusList = null;
		if (description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
			description=description.trim();
			projectStatusList = projectStatusService.selectProjectStatus(description);
		}
		else {
			projectStatusList = projectStatusService.selectProjectStatus();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<DicProjectStatus> pageInfo = new PageInfo<DicProjectStatus>(projectStatusList);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/dic/show-projectstatus";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  通过id删除项目状态字典信息
	* @Param:  DicProjectStatus projectStatus
	* @return:  1
	*/
	@RequestMapping("/deleteprojectstatus/{id}")
	@ResponseBody
	public String deleteProjectStatus(@ModelAttribute DicProjectStatus projectStatus){
		logger.debug("调用projectstatus/deleteprojectstatus");
		String result="0";
		boolean flag=false;
		try {
			flag=projectStatusService.deleteById(projectStatus.getId().intValue());
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
	* @Description:  删除选中的多个id,批量删除项目状态字典信息
	* @Param:  Long[] ids
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				projectStatusService.deleteById(Integer.parseInt(String.valueOf(id)));
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
	* @Param:  DicProjectStatus projectStatus
	* @return:
	*/
	@RequestMapping(value = "/updatebyid/{id}")
	public String updateByIdBefore(Model model, @ModelAttribute(value = "projectStatus") DicProjectStatus projectStatus,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		projectStatus = projectStatusService.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("projectStatus", projectStatus);
		return "admin/dic/editor-projectstatus";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  编辑完信息之后把项目状态字典信息更新到数据库
	* @Param:  DicProjectStatus projectStatus
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updateById(Model model, @ModelAttribute(value = "ProjectStatus") DicProjectStatus projectStatus,HttpServletRequest request) {
		logger.debug("调用projectStatus/updatebyid");
		int id = projectStatus.getId().intValue();
		DicProjectStatus result=new DicProjectStatus();
		try {
			projectStatusService.deleteById(id); // 删除选中的字典信息
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(projectStatus.getId());
			result.setDescription(request.getParameter("description1")); // 把原来的字典信息记录下来,若更新失败重新插回到数据库中
			DicProjectStatus projectStatus2=projectStatusService.selectProjectStatusByDescription(projectStatus.getDescription(),projectStatus.getCode());
			if(projectStatus2==null) {
				projectStatusService.insertProjectStatusSelective(projectStatus); // ProjectStatus代表更改过后的数据，插入到数据库中
				return "1";
			}
			else {
				projectStatusService.insertProjectStatusSelective(result); // result是改变之前的值，插入到数据库中
				return "2";
			}
		}
		catch (Exception e) {
			projectStatusService.insertProjectStatusSelective(result); // result是改变之前的值，插入到数据库中
			return "2";
		}
	}
}
