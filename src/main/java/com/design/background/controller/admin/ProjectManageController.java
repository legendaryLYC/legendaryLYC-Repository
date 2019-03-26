package com.design.background.controller.admin;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.design.background.config.Constant;
import com.design.background.entity.*;
import com.design.background.form.DesignerForm;
import com.design.background.form.MoneyListForm;
import com.design.background.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.design.background.form.ProjectForm;
import com.design.background.mapper.DicProjectComponentMapper;
import com.design.background.model.AreaModel;
import com.design.background.model.ResultData;
import com.design.background.util.StringToDate;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Author： 孟晓冬
 * @Date： 2019/2/13
 * @Description： 这是后台项目管理的增删改查controller
 */
@Controller
@RequestMapping("/admin/projectmanager")
public class ProjectManageController {

	@Autowired
	private DesignerManagementService designerManagementService;
	@Autowired
	private ProjectManageService projectManageService;
	@Autowired
	private DicService dicService;
	@Autowired
	private DicProjectTypeService dicProjectTypeService;
	@Autowired
	private MyProjectService myProjectService;
	@Autowired
    private ProjectFilesService projectFilesService;
	@Autowired
    private DicProjectComponentMapper dicProjectComponentMapper;

	/**
	 * @Description: 调用晓东接口，根据项目id查出该项目缺少工程类型id的数组
	 * @return: java.lang.String
	 * @Author: 李永成
	 */
	@Autowired
	DesignerRelationshipService designerRelationshipService;

	/**
	 * @Description: 调用晓东接口，根据缺少工程类型id的数组查出该项目还缺少哪些类型的设计师
	 * @return: java.lang.String
	 * @Author: 李永成
	 */
	@Autowired
	private DicProjectComponentService dicProjectComponentService;

	private static final Logger logger = LoggerFactory.getLogger(ProjectManageController.class);
    /**
     * @Description: 返回项目列表页的controller
     * @return: java.lang.String
     * @Author: 孟晓冬
     * @Date: 2019/2/13
     */
	@RequestMapping(value = { "", "/" })
    public String projectList(Model model,ProjectDetail projectDetail,
    		@RequestParam(value = "timeFrom", required = false) String timeFrom,
    		@RequestParam(value = "timeTo", required = false) String timeTo,
    		@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
    		@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize)
    {
		//日期字符转化为Date型,  发布日期筛选范围
		java.util.Date dateFrom = StringToDate.toUtilDate(timeFrom);
		java.util.Date dateTo = StringToDate.toUtilDate(timeTo);
		//分页
		PageHelper.startPage(pageNo, pageSize);
		List<ProjectForm> result = projectManageService.selectProjectsSelective(projectDetail, dateFrom, dateTo);
		PageInfo<ProjectForm> pageInfo = new PageInfo<ProjectForm>(result);
		if (pageNo > pageInfo.getPages()) {
			pageNo = pageInfo.getPages();
			PageHelper.startPage(pageNo, pageSize);
			result = projectManageService.selectProjectsSelective(projectDetail, dateFrom, dateTo);
			pageInfo = new PageInfo<ProjectForm>(result);
		}
		model.addAttribute("pageInfo", pageInfo);
		
		//回显查询条件
		model.addAttribute("project", projectDetail);
		model.addAttribute("timeFrom", dateFrom);
		model.addAttribute("timeTo", dateTo);
		
		//获取项目规模和项目状态字典
		List<DicProjectScale> models = dicService.getModels();
		List<DicProjectStatus> status = dicService.getProcess();
		model.addAttribute("models", models);
		model.addAttribute("status", status);
        return "admin/projectManage/projectManage";
    }
	
	/**
	 * 删除项目
	 */
	@ResponseBody
	@RequestMapping(value =  "/deleteproject", produces = "application/json;charset=utf-8")
	public ResultData deleteProject(@RequestParam(value = "id", required = true) Long id) {
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：projectManage/projectManage，传过来的参数为id=" + id );
		
		ResultData resultToPage = new ResultData();
		
		//删除该id的项目
		Boolean result = projectManageService.deleteProjectById(id);
		
		//返回结果
		if(result == true){
			resultToPage.setCode("000000");
			resultToPage.setMessage("删除成功！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("删除失败！");
		}
		return resultToPage;
	}
	
	/**
	 * 删除所有勾选的项目
	 */
	@ResponseBody
	@RequestMapping(value =  "/deleteallprojects", produces = "application/json;charset=utf-8")
	public ResultData deleteAllProjects(@RequestBody Long[] ids) {
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：admin/projectManage/projectManage，传过来的参数为ids=" + ids );
		ResultData resultToPage = new ResultData();
		
		//记录删除结果
		Boolean result=false;
		for (long id : ids) {
			//删除该id的项目
			result = projectManageService.deleteProjectById(id);
		}
		//返回结果
		if(result == true){
			resultToPage.setCode("000000");
			resultToPage.setMessage("删除成功！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("删除失败！");
		}
		return resultToPage;
	}

	/**
	 * 进入项目修改页面和项目详情页面
	 */
	@RequestMapping(value = "/updateprojectpage")
	public String updateProjectPage(Model model, @RequestParam(value = "isUpdate", required = true)Integer isUpdate,
			@RequestParam(value = "id", required = true)Long id) {
		//查询该id号的项目对象
		ProjectDetail project = new ProjectDetail();
		project.setId(id);
		//记录要修改的项目
		ProjectForm projectForm = null;
		try {
			projectForm = projectManageService.selectProjectsFormById(project);
		} catch (Exception e) {
			logger.info("id获取项目失败"+e.getMessage());
		}
		//加载该id号的项目
		model.addAttribute("projectForm", projectForm);
		if(isUpdate == 1) {
			//加载下拉菜单的字典List
			model.addAttribute("types", dicProjectTypeService.selectProjectType());
			model.addAttribute("province",dicService.getProvinces());
			model.addAttribute("models",dicService.getModels());
			model.addAttribute("stages",dicService.getStages());
			model.addAttribute("dispatchs",dicService.getDispatchs());
			model.addAttribute("processes",dicService.getProcess());
			return "admin/projectManage/updateProject";
		}else {
			//加载所需设计师类型
			List<String> types = new ArrayList<>(5);
			if(projectForm.getComponentCode() != null) {
				for(String typeString : projectForm.getComponentCode().split(",")) {
					String descri = dicProjectComponentMapper.selectByCode(Integer.parseInt(typeString)).getDescription();
					types.add(descri);
				}
			}
			model.addAttribute("types",types);
			//设计费分配
			List<MoneyDistribution> moneyList = myProjectService.selectMoneyByPrimaryKey(project.getId());
	        if(moneyList.size() != 0) {
	        	model.addAttribute("money1",moneyList.get(0));
	            model.addAttribute("money2",moneyList.get(1));
	        }
			return "admin/projectManage/projectDetail";
		}
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
	
//	/**
//	 * 修改项目
//	 */
//	@ResponseBody
//	@RequestMapping(value =  "/updateproject", produces = "application/json;charset=utf-8",method ={RequestMethod.GET})
//	public ResultData updateOrder(ProjectDetail projectDetail,
//			@RequestParam(value = "deadlineString", required = false) String deadlineString,
//			@RequestParam(value = "startupDateString", required = false) String startupDateString,
//			@RequestParam(value = "releaseTimeString", required = false) String releaseTimeString,
//			@RequestParam(value = "processTimeString", required = false) String processTimeString,
//			@RequestParam(value = "registrationDeadlineString", required = false) String registrationDeadlineString) {
//		/*
//		 * 打印入参日志
//		 */
//		logger.info("调用者为：admin/projectManage/updateProject，传过来的参数为projectDetail=" + 
//				projectDetail );
//		ResultData resultToPage = new ResultData();
//		//日期转换为Date型
//		Date deadline = StringToDate.toSqlDate(deadlineString);
//		Date startupDate = StringToDate.toSqlDate(startupDateString);
//		Date releaseTime = StringToDate.toSqlDate(startupDateString);
//		Date processTime = StringToDate.toSqlDate(processTimeString);
//		Date registrationDeadlineTime = StringToDate.toSqlDate(registrationDeadlineString);
//		projectDetail.setDeadline(deadline);
//		projectDetail.setStartupDate(startupDate);
//		projectDetail.setReleaseTime(releaseTime);
//		projectDetail.setReleaseTime(processTime);
//		projectDetail.setProcessTime(processTime);
//		projectDetail.setRegistrationDeadline(registrationDeadlineTime);
//		
//		Boolean result = false;
//		result = projectManageService.updateProjectAll(projectDetail);
//		
//		//返回结果
//		if(result == true){
//			resultToPage.setCode("000000");
//			resultToPage.setMessage("修改成功！");
//		}
//		else{
//			resultToPage.setCode("999999");
//			resultToPage.setMessage("修改失败！");
//		}
//		return resultToPage;
//	}
	
	/**
	* 
	* @Author 孟晓冬
	* @Description: TODO 进入修改项目页面
	* @param @return   
	* @return String    
	* @Date 2019年2月25日 下午2:40:58
	* @throws
	 */
	@RequestMapping("/toUpdateProject/{id}")
    public String toUpdateProject(Model model, HttpServletRequest req ,@ModelAttribute ProjectDetail project) {
		project = projectManageService.selectByPrimaryKey(project.getId());
    	//加载下拉菜单的字典List
        model.addAttribute("types", dicProjectTypeService.selectProjectType());
        model.addAttribute("province",dicService.getProvinces());
        model.addAttribute("models",dicService.getModels());
        model.addAttribute("stages",dicService.getStages());
        model.addAttribute("processes",dicService.getProcess());

        model.addAttribute("project",project);
        List<ProjectFiles> urlList = projectFilesService.getFilesByProjectId(project.getId());
        model.addAttribute("urlList",urlList);
        model.addAttribute("designerType" , dicProjectComponentService.selectProjectComponent());
        List<MoneyDistribution> moneyList = myProjectService.selectMoneyByPrimaryKey(project.getId());
        if(moneyList.size() != 0) {
        	model.addAttribute("money1",moneyList.get(0));
            model.addAttribute("money2",moneyList.get(1));
        }
        return"/admin/projectManage/project-edit";
       }
	
	/**
	 * 
	* @Author 孟晓冬
	* @Title: creatProject  
	* @Description: TODO 修改项目
	* @param @return   
	* @return String    
	* @Date 2019年2月25日 下午2:40:58
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/updateProject")
	public String updateProject(@ModelAttribute ProjectDetail project, @RequestParam("files") MultipartFile[] files,
			@ModelAttribute MoneyListForm moneyListForm, @RequestParam(value="designerTypes",required=true) String[] designerTypes,
			@RequestParam(value="image",required=false) MultipartFile image,@RequestParam(value="deleteIds",required=false) String deleteIds) {
		projectManageService.updateProject(project, designerTypes, files, moneyListForm, image,deleteIds);
		return "1";
	}
		
	/**
	 * 
	* @Author 孟晓冬 
	* @Title: deleteFile  
	* @Description: TODO 删除已上传文件
	* @param @param project
	* @param @param files
	* @param @return   
	* @return String    
	* @Date 2019年2月21日 上午12:18:03
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/deleteFile")
	public String deleteFile(@RequestParam(value="id",required=true) Long id) {
		int stat = projectFilesService.deleteByPrimaryKey(id);
		if(stat == 1) {
			return "success";
		}
		else {
			return "fail";
		}
	}
	
	/**
	 * 置顶项目
	 */
	@ResponseBody
	@RequestMapping(value =  "/topproject", produces = "application/json;charset=utf-8")
	public ResultData topProject(
			@RequestParam(value = "isTop", required = true) Integer isTop,
			@RequestParam(value = "id", required = true) Long id) {
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：admin/projectManage/projectManage，传过来的参数为id=" + id );
		
		ResultData resultToPage = new ResultData();
		
		//置顶该id的项目
		Boolean result = projectManageService.topProjectById(id,isTop);
		
		//返回结果
		if(result == true){
			resultToPage.setCode("000000");
			resultToPage.setMessage("删除成功！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("删除失败！");
		}
		return resultToPage;
	}

/**
* @Author: 李永成
* @Description: 填写添加项目需要的设计师信息页面
* @Param: id
* @return: 添加设计师页面
*/
@RequestMapping(value = "/adddesigners/{id}", produces = "application/json;charset=utf-8")
public String addDesignersBefore(Model model, @PathVariable("id") Long id)
{
	// 把项目id的内容显示在前台
	model.addAttribute("id", id);
	// 调用晓东接口得到项目还需要的分类
	Integer [] codeArray = designerRelationshipService.getUnselectedDesigners(id);
	List<DicProjectComponent> descriptions = new ArrayList<>();
	for(Integer code : codeArray) {
		DicProjectComponent description = dicProjectComponentService.selectByCode(code);
		descriptions.add(description);
	}
	// 传入分类的具体类型DicProjectComponent descriptions
	model.addAttribute("descriptions",descriptions);
	
	//显示该项目的设计师列表
	model.addAttribute("designers",designerRelationshipService.selectDesignersByProjectId(id));
	return "admin/projectManage/adddesigners";
}

	/**
	 * @Author: 李永成
	 * @Description:  选中设计师之后，添加设计师信息到数据库
	 * @Param:  电话号码:phone 设计师类别编号:typeCode
	 * @return:  1
	 */
	@ResponseBody
	@RequestMapping(value = "/adddesigners")
	public String addDesigners(
							   @RequestParam(value = "phone", required = false)String phone,
							   @RequestParam(value = "typeCode", required = false)Integer typeCode,
							   @RequestParam(value = "id", required = false)Long id
	) {
		logger.info("projectmanager/adddesigners");
		// 查看该设计师是否存在
		LeadingUser leadingUser = designerManagementService.selectDesignerByNum(phone);
		// 判断设计师是否已经加入这个项目
	try{
		Long designerId = leadingUser.getId();
		DesignerRelationship judge = new DesignerRelationship();
		judge = designerRelationshipService.selectJoinStatus(designerId,id);
		if(judge != null)
		{
			return "0";
		}
		// 为项目添加设计师
		DesignerRelationship designerRelationship = new DesignerRelationship(id,designerId,typeCode, Constant.JOINRESULT_JOINED);
			int result = designerRelationshipService.insertSelective(designerRelationship);
			if(result==1){
				return "1";
			}
			else{
				return "0";
			}
		}
	catch (Exception e){
			return "0";
		}
	}

	/**
	 * @Author: 李永成
	 * @Description:  下拉框改变改变之后，更新前台显示的设计师信息
	 * @Param:  电话号码:phone 设计师类别编号:typeCode
	 * @return: ResultData<List<DesignerForm>> result
	 */
	@ResponseBody
	@RequestMapping(value = "/showdesigners")
	public ResultData showDesigners(Model model,
								@RequestParam(value = "phone", required = false)String phone,
								@RequestParam(value = "type", required = false)String type)
	{
		logger.info("projectmanager/showdesigners");
		try{
			int typeCode = Integer.parseInt(type);
			List<DesignerForm> designerForm= designerManagementService.selectDesignerByPhone(phone,typeCode);
			model.addAttribute("designerForm",designerForm);
			ResultData<List<DesignerForm>> result = new ResultData();
			result.setCode("000000");
			result.setMessage("查询成功");
			result.setData(designerForm);
			return result;
		}
		catch (Exception e){
			ResultData result = new ResultData();
			result.setCode("999999");
			result.setMessage("查询出现异常");
			return result;
		}
	}
	
	
	/**
	 * 删除项目
	 */
	@ResponseBody
	@RequestMapping(value =  "/deletedesigner", produces = "application/json;charset=utf-8")
	public ResultData deleteDesigner(@RequestParam(value = "id", required = true) Long id) {
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：projectManage/adddesigners，传过来的参数为id=" + id );
		
		ResultData resultToPage = new ResultData();
		
		//删除该id的项目
		int result = designerRelationshipService.deleteByPrimaryKey(id);
		
		//返回结果
		if(result == 1){
			resultToPage.setCode("000000");
			resultToPage.setMessage("删除成功！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("删除失败！");
		}
		return resultToPage;
	}
	
}