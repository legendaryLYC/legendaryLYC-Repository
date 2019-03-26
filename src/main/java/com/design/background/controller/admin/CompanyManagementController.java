package com.design.background.controller.admin;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.design.background.entity.CompanyManagement;
import com.design.background.entity.DicColumn;
import com.design.background.service.AboutUsService;
import com.design.background.service.CompanyManagementService;
import com.design.background.util.DesignFileUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin/company")
public class CompanyManagementController {
		
		@Autowired
		private CompanyManagementService companyManagementService;
		/**
		 * 
		* @Author 任松
		* @Title: selectProjectType  
		* @Description: TODO 客户管理展示
		* @param @param model
		* @param @param pageNo
		* @param @param pageSize
		* @param @param description
		* @param @return   
		* @return String  
		* @Date 2019年2月26日 下午1:53:13
		* @throws
		 */
		@RequestMapping("/showlist")
		public String columnManagement(Model model,
				@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
				@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
				@RequestParam(value = "companyName", required = false, defaultValue = "") String companyName) {
			companyName = companyName.trim();
			PageHelper.startPage(pageNo, pageSize);
			List<CompanyManagement> list = companyManagementService.selectAll(companyName);
			for(int i = 0; i < list.size(); i++) {
				CompanyManagement companyManagement = (CompanyManagement)list.get(i);
				list.set(i, companyManagement);
			}
			PageInfo<CompanyManagement> pageInfo = new PageInfo<CompanyManagement>(list);
			System.out.println(pageInfo.getPages());
			if (pageNo > pageInfo.getPages()) {
				pageNo = pageInfo.getPages();
				PageHelper.startPage(pageNo, pageSize);
				list = companyManagementService.selectAll(companyName);
				for(int i = 0; i < list.size(); i++) {
					CompanyManagement companyManagement = (CompanyManagement)list.get(i);
					list.set(i, companyManagement);
				}
				pageInfo = new PageInfo<>(list);
			}
			model.addAttribute("companyName",companyName);
			model.addAttribute("pageInfo", pageInfo);
			return "admin/companymanagement/list";
		}
		
		/**
		 * 
		* @Author 任松
		* @Title: toaddcompany  
		* @Description: TODO 跳转到添加栏目页面 
		* @param @return   
		* @return String    
		* @Date 2019年2月26日 下午4:34:20
		* @throws
		 */
		@RequestMapping(value = "/toaddcompany")
		public String toAddCompany() {
			return "admin/companymanagement/add";
		}
		
		/**
		 * 
		* @Author 任松
		* @Title: addColumn  
		* @Description: TODO 添加客户
		* @param @param model
		* @param @param dicColumn
		* @param @return   
		* @return int    
		* @Date 2019年2月26日 下午4:17:43
		* @throws
		 */
		@ResponseBody
		@RequestMapping(value = "/addcompany")
		public int addCompany(Model model, @ModelAttribute CompanyManagement companyManagement,@RequestParam(name="companyphoto",required=false) MultipartFile companyphoto) {
			companyManagement.setCreateTime(new Date());
			// 上传图片
			String headImagePath=null;
			if(companyphoto != null){
	            //上传到阿里云，从阿里云返回一个地址
	            headImagePath = DesignFileUtils.uploadSingleFile(companyphoto);
	            // 存入路径
	            if(StringUtils.isNoneBlank(headImagePath)){
	            	companyManagement.setPhoto(headImagePath); 
	            }
	        }
			
			int flag = companyManagementService.insert(companyManagement);
			System.out.println(flag);
			return flag;
		}
		
		
		@ResponseBody
		@RequestMapping(value = "/deletecompanyById/{id}")
		public int deleteColumById(@ModelAttribute CompanyManagement companyManagement) {
			int flag = companyManagementService.deleteByPrimaryKey(companyManagement.getId());
			return flag;
		}
		
		
		/**
		 * 
		* @Author 任松
		* @Title: deleteColum  
		* @Description: TODO 批量删除栏目
		* @param @param id
		* @param @return   
		* @return String    
		* @Date 2019年2月26日 下午3:56:14
		* @throws
		 */
		@RequestMapping("/deleteCompanyByIds")
		@ResponseBody
		public Integer delete(@RequestBody Long[] ids) {
			if (ids.length != 0 && ids != null) {
				for (long id : ids) {
					companyManagementService.deleteByPrimaryKey(id);
				}
				return 1;
			}
			return 0;
		}
		
		/**
		 * 
		* @Author 任松
		* @Title: toEditColumn  
		* @Description: TODO  跳转到修改客户页面
		* @param @param model
		* @param @param dicColumn
		* @param @return   
		* @return String    
		* @Date 2019年2月15日 下午4:54:44
		* @throws
		 */
		@RequestMapping("/tocompanyedit/{id}")
		public String toCompanyEdit(Model model,@ModelAttribute CompanyManagement companyManagement) {
			companyManagement = companyManagementService.selectByPrimaryKey(companyManagement.getId());
			model.addAttribute("companyManagement",companyManagement);
			return "admin/companymanagement/edit";
		}
		
		/**
		 * 
		* @Author 任松 
		* @Title: editColumn  
		* @Description: TODO 修改客户
		* @param @param model
		* @param @param dicColumn
		* @param @return   
		* @return int    
		* @Date 2019年2月15日 下午4:57:38
		* @throws
		 */
		@ResponseBody
		@RequestMapping(value = "/editcompany")
		public int editCompany(Model model, @RequestParam(name="companyid",required=false) Long id,@RequestParam(name="photo",required=false) MultipartFile photo,@RequestParam(name= "companyName",required = false) String companyName)
		{
			CompanyManagement companyManagement=companyManagementService.selectByPrimaryKey(id);
			companyManagement.setUpdateTime(new Date());
			companyManagement.setCompanyName(companyName);
			// 上传图片
			String headImagePath=null;
			if(photo != null){
	            //上传到阿里云，从阿里云返回一个地址
	            headImagePath = DesignFileUtils.uploadSingleFile(photo);
	            // 存入路径
	            if(StringUtils.isNoneBlank(headImagePath)){
	            	companyManagement.setPhoto(headImagePath); 
	            }
	        }
			
			int flag = companyManagementService.updateByPrimaryKeyWithBLOBs(companyManagement);
			return flag;
		}	
}
