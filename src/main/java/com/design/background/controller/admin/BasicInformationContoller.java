package com.design.background.controller.admin;

import com.design.background.entity.BasicInformation;
import com.design.background.form.DesignerForm;
import com.design.background.util.DesignFileUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.design.background.service.BasicInformationService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：宋博通
 * @Date： 2019/2/26
 * @Description： 这是页面基础信息修改的controller
 */
@Controller
@RequestMapping("/admin/basicInformationManagement")
public class BasicInformationContoller {
	
	@Autowired
	private BasicInformationService basicInformationService;

	/**
	 *基础信息列表页面
	 *
	 */
	@RequestMapping("/list")
    public String BasicInformationEdit(Model model,
		   @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
		   @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize)
	{
		//分页
		PageHelper.startPage(pageNo, pageSize);
		List<BasicInformation> result = basicInformationService.selectInformationList();
		PageInfo<BasicInformation> pageInfo = new PageInfo<BasicInformation>(result);
		if (pageNo > pageInfo.getPages()) {
			pageNo = pageInfo.getPages();
			PageHelper.startPage(pageNo, pageSize);
			result = basicInformationService.selectInformationList();
			pageInfo = new PageInfo<BasicInformation>(result);
		}
		model.addAttribute("pageInfo",pageInfo);
		//System.out.println(result.get(0).getEmailAddress());
		return "admin/basicinformationManagement/list";
    }
	
	//查找
		@RequestMapping("/search")
	    public String SerchList(Model model,String designername,
	    		@RequestParam(value = "titlename", required = false) String titlename,
	    		@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
	    		@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize)
	    {      	
			System.out.println(titlename);
			//分页
	    	PageHelper.startPage(pageNo, pageSize);
	    	List<BasicInformation> result = basicInformationService.selectByname(titlename);
	    	PageInfo<BasicInformation> pageInfo = new PageInfo<BasicInformation>(result);
	    	if (pageNo > pageInfo.getPages()) {
	    		pageNo = pageInfo.getPages();
	    		PageHelper.startPage(pageNo, pageSize);
	    		result = basicInformationService.selectByname(titlename);
	    		pageInfo = new PageInfo<BasicInformation>(result);
	    	}
	 
	    	model.addAttribute("titlename",titlename);
	        model.addAttribute("pageInfo",pageInfo);
	        return "admin/basicinformationManagement/list";
	    }

	/**
	 * 应用设置
	 */
	@ResponseBody
	@RequestMapping(value =  "/set", produces = "application/json;charset=utf-8")
	public String set(@RequestParam(value = "id", required = true) Long id) {

		int result  = basicInformationService.applySetting(id);
		//返回结果
		if(result == 1){
			return "1";
		}
		else{
			return "0";
		}
	}

	/**
	 * 删除设置项
	 */
	@ResponseBody
	@RequestMapping(value =  "/deleteone", produces = "application/json;charset=utf-8")
	public String deleteOne(@RequestParam(value = "id", required = true) Long id) {

		int result  = basicInformationService.deleteInformation(id);
		//返回结果
		if(result == 1){
			return "1";
		}
		else{
			return "0";
		}
	}

	/**
	 * 删除所有勾选的项
	 */
	@ResponseBody
	@RequestMapping(value =  "/deleteall", produces = "application/json;charset=utf-8")
	public String deleteAll(@RequestBody Long[] ids) {

		//记录删除结果
		int result = 0 ;
		for (long id : ids) {
			//删除该id的项目
			result = basicInformationService.deleteInformation(id);
		}
		//返回结果
		if(result > 0){
			return "1";
		}
		else{
			return "0";
		}
	}

	/**
	 * 进入基础信息修改页面和详情页面
	 */
	@RequestMapping(value = "/editpage")
	public String updateProjectPage(Model model,
				@RequestParam(value = "isUpdate", required = true)Integer isUpdate,
				@RequestParam(value = "id", required = true)Long id) {
		//查询该id号的对象
		BasicInformation bas = null;
		bas = basicInformationService.selectByid(id);

		//加载该id号的项目
		model.addAttribute("basicInformation", bas);

		if(isUpdate == 1) {

			return "admin/basicinformationManagement/edit";
		}else {

			return "admin/basicinformationManagement/add";
		}
	}

	/**
	 * 更新设置项
	 */
	@ResponseBody
	@RequestMapping(value =  "/edit", produces = "application/json;charset=utf-8")
	public String edit(BasicInformation basicInformation,
					   @RequestParam(value="headImage", required=false) MultipartFile headImage,
					   @RequestParam(value="footImage", required=false) MultipartFile footImage,
					   @RequestParam(value="wechat", required=false) MultipartFile wechat) {
		//上传到阿里云
		if(headImage != null){
			String head = DesignFileUtils.uploadSingleFile(headImage);
			basicInformation.setTitleImg(head);
		}
		//上传到阿里云
		if(footImage != null){
			String foot = DesignFileUtils.uploadSingleFile(footImage);
			basicInformation.setFootImg(foot);
		}
		//上传到阿里云
		if(wechat != null){
			String we = DesignFileUtils.uploadSingleFile(wechat);
			basicInformation.setWechatImg(we);
		}

		int result  = basicInformationService.updateInformationSelective(basicInformation);
		//返回结果
		if(result == 1){
			return "1";
		}
		else{
			return "0";
		}
	}

	/**
	 * 进入基础信息增加页面
	 */
	@RequestMapping(value = "/addpage")
	public String addPage(Model model) {
		return "admin/basicinformationManagement/add";
	}

	/**
	 * 增加设置项
	 */
	@ResponseBody
	@RequestMapping(value =  "/add", produces = "application/json;charset=utf-8")
	public String add(BasicInformation basicInformation,
					  @RequestParam(value="headImage", required=false) MultipartFile headImage,
					  @RequestParam(value="footImage", required=false) MultipartFile footImage,
					  @RequestParam(value="wechat", required=false) MultipartFile wechat) {
		//上传到阿里云
		if(headImage != null){
			String head = DesignFileUtils.uploadSingleFile(headImage);
			basicInformation.setTitleImg(head);
		}
		//上传到阿里云
		if(footImage != null){
			String foot = DesignFileUtils.uploadSingleFile(footImage);
			basicInformation.setFootImg(foot);
		}
		//上传到阿里云
		if(wechat != null){
			String we = DesignFileUtils.uploadSingleFile(wechat);
			basicInformation.setWechatImg(we);
		}

		int result  = basicInformationService.insertInformationSelective(basicInformation);
		//返回结果
		if(result == 1){
			return "1";
		}
		else{
			return "0";
		}
	}
}