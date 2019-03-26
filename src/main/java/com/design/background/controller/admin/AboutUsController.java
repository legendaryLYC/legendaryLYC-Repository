package com.design.background.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.background.entity.DicColumn;
import com.design.background.entity.DicProjectType;
import com.design.background.entity.SysResource;
import com.design.background.entity.SysRole;
import com.design.background.entity.SysRoleResources;
import com.design.background.service.AboutUsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: AboutUsController  
* @Description: TODO(关于我们的管理)  
* @author HRX  
* @date 2019年2月15日  
*
 */
@Controller
@RequestMapping("/admin/aboutUs")
public class AboutUsController {
	
	@Autowired
	private AboutUsService aboutUsService;
	
	/**
	 * 
	* @Author 黄润宣 
	* @Title: selectProjectType  
	* @Description: TODO 关于我们栏目展示
	* @param @param model
	* @param @param pageNo
	* @param @param pageSize
	* @param @param description
	* @param @return   
	* @return String  
	* @Date 2019年2月15日 下午1:53:13
	* @throws
	 */
	@RequestMapping("/columnManagement")
	public String columnManagement(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "description", required = false, defaultValue = "") String description) {
		description = description.trim();
		PageHelper.startPage(pageNo, pageSize);
		List<DicColumn> list = aboutUsService.selectAll(description);
		for(int i = 0; i < list.size(); i++) {
			DicColumn dicColumn = (DicColumn)list.get(i);
			list.set(i, dicColumn);
		}
		PageInfo<DicColumn> pageInfo = new PageInfo<DicColumn>(list);
		System.out.println(pageInfo.getPages());
		if (pageNo > pageInfo.getPages()) {
			pageNo = pageInfo.getPages();
			PageHelper.startPage(pageNo, pageSize);
			list = aboutUsService.selectAll(description);
			for(int i = 0; i < list.size(); i++) {
				DicColumn dicColumn = (DicColumn)list.get(i);
				list.set(i, dicColumn);
			}
			pageInfo = new PageInfo<>(list);
		}
		model.addAttribute("description",description);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/aboutUs/list";
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: toAddColumn  
	* @Description: TODO 跳转到添加栏目页面 
	* @param @return   
	* @return String    
	* @Date 2019年2月15日 下午4:34:20
	* @throws
	 */
	@RequestMapping(value = "/toAddColumn")
	public String toAddColumn() {
		return "admin/aboutUs/add-Column";
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: addColumn  
	* @Description: TODO 添加栏目
	* @param @param model
	* @param @param dicColumn
	* @param @return   
	* @return int    
	* @Date 2019年2月15日 下午4:17:43
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/addColumn")
	public int addColumn(Model model, @ModelAttribute DicColumn dicColumn) {
		dicColumn.setUpdatetime(new Date());
		int flag = aboutUsService.insert(dicColumn);
		System.out.println(flag);
		return flag;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/deleteColumById/{id}")
	public int deleteColumById(@ModelAttribute DicColumn dicColumn) {
		int flag = aboutUsService.deleteByPrimaryKey(dicColumn.getId());
		return flag;
	}
	
	
	/**
	 * 
	* @Author HRX 
	* @Title: deleteColum  
	* @Description: TODO 批量删除栏目
	* @param @param id
	* @param @return   
	* @return String    
	* @Date 2019年2月15日 下午3:56:14
	* @throws
	 */
	@RequestMapping("/deleteColumByIds")
	@ResponseBody
	public Integer delete(@RequestBody Long[] ids) {
		if (ids.length != 0 && ids != null) {
			for (long id : ids) {
				aboutUsService.deleteByPrimaryKey(id);
			}
			return 1;
		}
		return 0;
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: toEditColumn  
	* @Description: TODO  跳转到修改栏目页面
	* @param @param model
	* @param @param dicColumn
	* @param @return   
	* @return String    
	* @Date 2019年2月15日 下午4:54:44
	* @throws
	 */
	@RequestMapping("/toEditColumn/{id}")
	public String toEditColumn(Model model,@ModelAttribute DicColumn dicColumn) {
		dicColumn = aboutUsService.selectByPrimaryKey(dicColumn.getId());
		model.addAttribute("column",dicColumn);
		return "admin/aboutUs/edit-Column";
	}
	
	/**
	 * 
	* @Author HRX 
	* @Title: editColumn  
	* @Description: TODO 修改栏目
	* @param @param model
	* @param @param dicColumn
	* @param @return   
	* @return int    
	* @Date 2019年2月15日 下午4:57:38
	* @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/editColumn")
	public int editColumn(Model model, @ModelAttribute(value = "role") DicColumn dicColumn) {
		dicColumn.setUpdatetime(new Date());
		int flag = aboutUsService.updateByPrimaryKeyWithBLOBs(dicColumn);
		return flag;
	}
	
}
