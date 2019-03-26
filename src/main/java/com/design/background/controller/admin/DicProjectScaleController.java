package com.design.background.controller.admin;

import com.design.background.entity.DicProjectScale;
import com.design.background.service.DicProjectScaleService;
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
 * 项目规模字典类
 * @author 李永成
 */

@Controller
@RequestMapping("admin/projectscale")
public class DicProjectScaleController {
	
	private static final Logger logger = LoggerFactory.getLogger(DicProjectScaleController.class);
	
	@Autowired
	private DicProjectScaleService projectScaleService; //操作项目规模的Service类

	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description: 添加项目规模字典信息
	* @Param: Model
	* @return: dic/add-projectScale
	*/
	@RequestMapping(value = "/addprojectscale")
	public String addProjectScaleBefore(Model model) {
		return "admin/dic/add-projectscale";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  添加项目规模字典信息
	* @Param:  code,description
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/insertprojectscale")
	public String insertProjectScale(Model model,
			@RequestParam(value = "code", required = false, defaultValue="") String code,
			@RequestParam(value = "description", required = false, defaultValue="") String description){
		int code1 = Integer.parseInt(code);
		try {
			DicProjectScale ProjectScale=projectScaleService.selectProjectScaleByDescription(description,code1);
			if(ProjectScale!=null) {
				return "2"; // 代表数据库中已有该商品
			}
			else {
				boolean flag = projectScaleService.insertProjectScale(code1,description);
				if(flag == true) {
					DicProjectScale cache = new DicProjectScale();
					cache.setCode(code1);
					cache.setDescription(description);
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
	* @Description:  通过输入描述信息查询项目规模字典信息
	* @Param:  description,pageNo,pageNo
	* @return:  dic/show-projectScale
	*/
	//通过项目规模名查询项目规模
	@RequestMapping("/selectprojectscale")
	public String selectProjectScale(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "description", required = false, defaultValue="") String description) {
		List<DicProjectScale> projectScaleList = null;
		if (description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
		}
		PageHelper.startPage(pageNo, pageSize);
		if(description != null && !"".equals(description.trim())) {
			model.addAttribute("description", description.trim());
			description=description.trim();
			projectScaleList = projectScaleService.selectProjectScale(description);
		}
		else {
			projectScaleList = projectScaleService.selectProjectScale();
		}
		PageHelper.startPage(pageNo, pageSize);
		PageInfo<DicProjectScale> pageInfo = new PageInfo<DicProjectScale>(projectScaleList);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/dic/show-projectscale";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  通过id删除项目规模字典信息
	* @Param:  DicProjectScale projectScale
	* @return:  1
	*/
	@RequestMapping("/deleteprojectscale/{id}")
	@ResponseBody
	public String deleteProjectScale(@ModelAttribute DicProjectScale projectScale){
		logger.debug("调用ProjectScale/deleteProjectScale");
		String result="0";
		boolean flag=false;
		try {
			flag=projectScaleService.deleteById(projectScale.getId().intValue());
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
	* @Description:  删除选中的多个id,批量删除项目规模字典信息
	* @Param:  Long[] ids
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/deleteAll", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
				projectScaleService.deleteById(Integer.parseInt(String.valueOf(id)));
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
	* @Param:  DicProjectScale projectScale
	* @return:
	*/
	@RequestMapping(value = "/updatebyid/{id}")
	public String updateByIdBefore(Model model, @ModelAttribute(value = "projectScale") DicProjectScale projectScale,
			@PathVariable("id") Long id) {
		// 根据ID查询用户 和对应的角色
		projectScale = projectScaleService.selectByPrimaryKey(id);
		// 查询对应用户的角色
		model.addAttribute("projectScale", projectScale);
		return "admin/dic/editor-projectscale";
	}
	/*
	* @Author: 李永成
	* @Date: 2019/2/13
	* @Description:  编辑完信息之后把项目规模字典信息更新到数据库
	* @Param:  DicProjectScale projectScale
	* @return:  1
	*/
	@ResponseBody
	@RequestMapping(value = "/updatebyid", params = "save=true")
	public String updateById(Model model, @ModelAttribute(value = "ProjectScale") DicProjectScale projectScale,HttpServletRequest request) {
		logger.debug("调用ProjectScale/updatebyid");
		int id = projectScale.getId().intValue();
		DicProjectScale result=new DicProjectScale();
		try {
			projectScaleService.deleteById(id); // 删除选中的字典信息
			result.setCode(Integer.parseInt(request.getParameter("code1")));
			result.setId(projectScale.getId());
			result.setDescription(request.getParameter("description1")); // 把原来的字典信息记录下来,若更新失败重新插回到数据库中
			DicProjectScale projectScale2=projectScaleService.selectProjectScaleByDescription(projectScale.getDescription(),projectScale.getCode());
			if(projectScale2==null) {
				projectScaleService.insertProjectScaleSelective(projectScale); // ProjectScale代表更改过后的数据，插入到数据库中
				return "1";
			}
			else {
				projectScaleService.insertProjectScaleSelective(result); // result是改变之前的值，插入到数据库中
				return "2";
			}
		}
		catch (Exception e) {
			projectScaleService.insertProjectScaleSelective(result); // result是改变之前的值，插入到数据库中
			return "2";
		}
	}
}
