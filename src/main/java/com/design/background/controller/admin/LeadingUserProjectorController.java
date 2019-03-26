package com.design.background.controller.admin;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.design.background.entity.DicArea;
import com.design.background.entity.DicCity;
import com.design.background.entity.LeadingUser;
import com.design.background.form.ProjectForm;
import com.design.background.form.ProjectorForm;
import com.design.background.model.ResultData;
import com.design.background.service.DicService;
import com.design.background.service.LeadingUserProjectorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.design.background.util.DesignFileUtils;
import com.design.background.util.StringToDate;


/**用户信息管理，包含前台和后台
 * @author 任松
 * 2019.2.15 18：32
 */
@Controller
@RequestMapping("/admin/projector")
public class LeadingUserProjectorController {

	private static final Logger logger = LoggerFactory.getLogger(LeadingUserProjectorController.class);

	@Autowired
	private LeadingUserProjectorController leadingUserProjectorController;
	
	@Autowired
	private LeadingUserProjectorService leadingUserProjectorService; // 调用LeadingUserProjectorService管理接口。

	@Autowired
	private DicService dicService; // 调用DicService中的方法获取字典
	/**
	 * 查询项目方信息(测试成功)
	 * @Param pageNo,pageSize,name
	 * @return
	 */
	@RequestMapping(value = "/find")
	public String find(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			HttpServletRequest request) {
		/*
		 * 打印入参日志
		 */
		logger.info("====== 进入find方法，调用者为xxx，" +  ",参数名pageNo : "+pageNo+"#pageSize : " + pageSize + "#name : " + name + "===========");
		
		//封装查询使用的leadingUser
		LeadingUser leadingUser=new LeadingUser();
		if (name != null && !"".equals(name.trim())) {
			leadingUser.setUsername(name.trim());
			model.addAttribute("name", name.trim());
		}
		name=name.trim();
		
		//分页，两者顺序不能调换
		PageHelper.startPage(pageNo, pageSize);
		List<LeadingUser> list = leadingUserProjectorService.select(name);
		logger.info("============查询出leadingUsers ： " + list.toString() + " ===========");
		
		try {	
			PageInfo<LeadingUser> pageInfo = new PageInfo<LeadingUser>(list);
			if (pageNo > pageInfo.getPages()) {
				pageNo = pageInfo.getPages();
				PageHelper.startPage(pageNo, pageSize);
				list = leadingUserProjectorService.select(name);
				pageInfo = new PageInfo<LeadingUser>(list);
			}
			model.addAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "admin/projectors/showList"; //返回显示的页面
	}
	
	
	/**
	 * 删除单一项目方  (测试成功)
	 * @Param model id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/del")
	public ResultData delete(Model model, @RequestParam(value="id") Long id
			) {
		logger.info("====== 进入delete方法，参数名id : "+id==null?null:id+" ===========");
		int key=0;
	 	key=leadingUserProjectorService.deleteById(id);
	 	logger.info("========== 删除ID为"+id+"的项目方结果 ： "+key+" ===============");
	 		
 		/*
		 * 打印出参日志
		 */
	 	ResultData result;
	 	result = ResultData.setCodeAndMessage(key, "success", "error");
	 	return result;
	}

	/**
	 * 批量删除项目方信息  (测试成功)
	 * @Param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delAll")
	public ResultData deleteAll(@RequestBody Long[] ids
			) {
		/*
		 * 打印入参方法
		 */
		logger.info("====== 进入deleteAll方法，参数名ids : "+ids==null?null:ids.toString()+" ===========");
		int key = 0;
		ResultData result;
		try {
			for(int i = 0 ; i < ids.length ; i++) {
				  key = leadingUserProjectorService.deleteById(ids[i]);
				  logger.info("========== 删除编号为"+ids[i]+"的物料结果  ："+key+" ===============");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = ResultData.setCodeAndMessage(0, "success", "error");
			return result;
		}	
		
	 	/*
		 * 打印出参日志
		 */	
	 	result = ResultData.setCodeAndMessage(key, "success", "error");
	 	return result;
	}
	
	/**
	 * 更新项目方信息
	 * @Param model,leadingUser,oldName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/update",produces = "application/json;charset=utf-8",method ={RequestMethod.POST})
	public ResultData update(Model model,LeadingUser leadingUser,@RequestParam(value="oldName") String oldName,
			@RequestParam(value = "create_Time", required = false) String createTime,
			@RequestParam(value = "update_Time", required = false) String updateTime,
			@RequestParam(name= "areaId",required = false) Integer area,
			@RequestParam(name= "cityId",required = false) Integer cityId,
			@RequestParam(name= "provinceId",required = false) Integer provinceId, 
			@RequestParam(name="headImage",required=false) MultipartFile headImage
			) {
		/*,@RequestParam(value = "oldName") String oldName
		 * 打印入参方法
		 */
		logger.info("====== 进入update方法，leadingUser : "+ leadingUser.toString() +"===========");
		ResultData result ;
		result = leadingUserProjectorController.judgeExist(leadingUser.getUsername(), oldName);
		if(result.getCode() == "999999") {
			return result;
		}
		String headImagePath=null;
		if(headImage != null){
            //上传到阿里云，从阿里云返回一个地址
            headImagePath = DesignFileUtils.uploadSingleFile(headImage);
            // 存入路径
            if(StringUtils.isNoneBlank(headImagePath)){
            	leadingUser.setPhoto(headImagePath); 
            }
        }
		//String日期转换为Date
		Date date1 = StringToDate.toSqlDate(createTime);
		leadingUser.setCreateTime(updateTime);
		Date date2 = StringToDate.toSqlDate(updateTime);
		leadingUser.setUpdateTime(date2);
		leadingUser.setArea(area);
		leadingUser.setCityId(cityId);
		leadingUser.setProvinceId(provinceId);
		leadingUser.setUsername(leadingUser.getUsername().trim());
		leadingUserProjectorService.updateById(leadingUser);
		logger.info("性别代码："+leadingUser.getSexType()+"");
		return result;
	}
	
	/**
	 * 跳转到添加项目方页面   (测试成功)
	 * @Param model
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd(Model model) {
		model.addAttribute("provinces",dicService.getProvinces());
		return "admin/projectors/add";
		
	}
	
	/**
	 * 跳转到修改项目方信息页面  (测试成功)
	 * @Param model,materielName
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public String editProjector(Model model,
			@RequestParam(value = "id", required = false, defaultValue = "1") Long id) {
		/*
		 * 打印入参
		 */
		logger.info("LeadingUserProjectorController下的edit方法，调用者xxx，id : " + id);
	//	List<LeadingUser> leadingUser = new ArrayList<>();
		LeadingUser leadingUser=new LeadingUser();
		//查询当前修改项目方的信息
		leadingUser=leadingUserProjectorService.selectById(id);
		logger.info("======== 修改项目方信息页面呈现信息 : " + leadingUser.toString() + " ==========");
		model.addAttribute("leadingUser", leadingUser);		
	
		
		//记录要修改的项目
		ProjectorForm projectorForm = new ProjectorForm();
		try {
			projectorForm = leadingUserProjectorService.selectUserAreaById(id, leadingUser.getArea());
		} catch (Exception e) {
			logger.info("id获取项目失败"+e.getMessage());
		}
		model.addAttribute("projectorForm", projectorForm);
		//加载下拉菜单的字典List
		model.addAttribute("roles",dicService.getRoles());
		model.addAttribute("provinces",dicService.getProvinces());
		model.addAttribute("leadingUser",leadingUser);
	//	model.addAttribute("projectForm", projectForm);
		return "admin/projectors/edit"; // 跳转到编辑页面
	}
	
	
	/**
	 * 如果是插入，判断是否存在该名字
	 * 如果是修改，判断是否存在，如果存在再判断和更改之前的名字相不相同
	 * @param newName,oldName
	 * @return
	 */
	public ResultData judgeNameExist(String newName,String oldName) {
		logger.info("进入LeadingUserProjectorController下的judgeNameExist方法，参数newName ： " + newName + " #oldName : " +oldName);
		List<LeadingUser> leadingUsers = leadingUserProjectorService.select(newName);
		int key = 0;
		ResultData result;
		if(leadingUsers.size() == 0||leadingUsers.get(0).getUsername().equals(oldName)) {
			key = 1;
		}
		/*
		 * 打印出参日志
		 */	
	 	result = ResultData.setCodeAndMessage(key, "success", "存在该名字！");
	 	return result;
	}
	
	public ResultData judgeExist(String newName,String oldName) {
		logger.info("进入LeadingUserProjectorController下的judgeNameExist方法，参数newName ： " + newName + " #oldName : " +oldName);
		List<LeadingUser> leadingUsers = leadingUserProjectorService.selectByName(newName);
		int key = 0;
		ResultData result;
		if(leadingUsers.size() == 0||leadingUsers.get(0).getUsername().equals(oldName)) {
			key = 1;
		}
		/*
		 * 打印出参日志
		 */	
	 	result = ResultData.setCodeAndMessage(key, "success", "存在该名字！");
	 	return result;
	}
	
	/**
	 * 添加项目方信息(测试成功)
	 * @Param model,leadingUsers
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/add",produces = "application/json;charset=utf-8",method ={RequestMethod.POST})
	public ResultData add(Model model,LeadingUser leadingUser,
			@RequestParam(value = "areaId", required = false) Integer area,
			@RequestParam(value = "cityId", required = false) Integer cityId,
			@RequestParam(value = "provinceId", required = false) Integer provinceId,
			@RequestParam(name="headImage",required=false) MultipartFile headImage
			//,@RequestParam(value = "time", required = false) String time,@RequestParam(value = "update_time", required = false) String updateTime
			) {
	
		logger.info("======进入add方法，参数名leadingUser : " + leadingUser.toString() + "===========");
		// 判断该用户在数据库中是否存在
		
		
		ResultData result=new ResultData();
	    result.setCode("999999");
		if(leadingUserProjectorService.selectByTel(leadingUser.getTel())!=0) {
			result.setCode("000000");
		}
		if(result.getCode() == "999999") {
			result.setMessage("该账号已存在");
			return result;
		}
		//String日期转换为Date
		//Date date = StringToDate.toSqlDate(time);
		Date date=new Date(System.currentTimeMillis());
		leadingUser.setCreateTime(date);
		//Date date1 = StringToDate.toSqlDate(updateTime);
		leadingUser.setUpdateTime(date);
		leadingUser.setArea(area);
		leadingUser.setCityId(cityId);
		leadingUser.setProvinceId(provinceId);
		String headImagePath=null;
		if(headImage != null){
            //上传到阿里云，从阿里云返回一个地址
            headImagePath = DesignFileUtils.uploadSingleFile(headImage);
            // 存入路径
            if(StringUtils.isNoneBlank(headImagePath)){
            	leadingUser.setPhoto(headImagePath); 
            }
        }
		
		//防止空格
		leadingUser.setUsername(leadingUser.getUsername().trim());
		if(leadingUserProjectorService.insert(leadingUser)==1) {
			result.setCode("000000");
		}else {
			result.setMessage("添加失败");
		}
	 	return result;

	}
	
	/**
	 * ajax返回要求的省市区字典
	 */
	@ResponseBody
	@RequestMapping(value =  "/getareadic", produces = "application/json;charset=utf-8",method ={RequestMethod.POST})
	public Object[] getAreaDic(
			@RequestParam(value = "who", required = false)String who,
			@RequestParam(value = "code", required = false)String code) {
		Object[] objects = new Object[0];
		//分类讨论是什么的code
		if("province".equals(who)) {
			List<DicCity> citys = dicService.getCitysByProvinceId(code);
			objects = citys.toArray();
		}else if("city".equals(who)) {
			List<DicArea> areas = dicService.getAreaByCityId(code);
			objects = areas.toArray();
		}
		return objects;
	}
	
	
	

	
}



