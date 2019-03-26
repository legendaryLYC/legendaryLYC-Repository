package com.design.background.controller.front;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.design.background.common.controller.BaseController;
import com.design.background.entity.*;
import com.design.background.form.MoneyListForm;
import com.design.background.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.design.background.config.FrontConfig;
import com.design.background.model.MyProjectModel;
import com.design.background.model.ResultData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: MyProjectController  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author HRX  
* @date 2019年2月16日  
*
 */
@Controller
@RequestMapping("/center/myProject")
public class MyProjectController extends BaseController {
	
	@Autowired
	private MyProjectService myProjectService;
	
    @Autowired
    private DicProjectTypeService projectTypeService;
    
    @Autowired
    private DicProjectStatusService projectStatusService;

    @Autowired
	private HttpSession httpSession;
    
    @Autowired
	private DicService dicService;
    
    @Autowired
	private DicProjectTypeService dicProjectTypeService;
    
    @Autowired
    private ProjectFilesService projectFilesService;
    
    @Autowired
    private ProjectManageService projectManageService;
    
    @Autowired
    private DicProjectComponentService dicProjectComponentService;

    @Autowired
	private MessageManageService messageManageService;

    @Autowired
	private ApplicationService applicationService;

	@Autowired
	private MessageGetService messageGetService;

    private static final Logger logger = LoggerFactory.getLogger(MyProjectController.class);
    
	/**
    *
   * @Author HRX
   * @Title: myProject
   * @Description: TODO 跳转到我的项目
   * @param @param model
   * @param @param req
   * @param @return
   * @return String
   * @Date 2019年2月16日 下午3:54:47
   * @throws
    */
   @RequestMapping(value= {"","/"})
   public String myProject(Model model, HttpServletRequest req ,@ModelAttribute ProjectDetail project,
   		@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
   		@RequestParam(value = "pageSize", required = false, defaultValue = "7") Integer pageSize) {

	   LeadingUser leadingUser=new LeadingUser();
	   leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
	   String acceptUser = leadingUser.getTel();

	   	PageHelper.startPage(pageNo, pageSize);
	   	List<MyProjectModel> list = myProjectService.selectByUserId(project);
	   	List<DicProjectType> typeList = projectTypeService.selectProjectType();
	   	List<DicProjectStatus> statusList = projectStatusService.selectProjectStatus();
	   //查找全部消息
	   List<NotificationManage> messagelist = messageGetService.selectByacceptUser(acceptUser,leadingUser.getCreateTime());
	   //查找已读消息
	   List<NotificationView> readedlist = messageGetService.selectreadedMessage(leadingUser.getId(),leadingUser.getCreateTime());



	   	PageInfo<MyProjectModel> pageInfo = new PageInfo<MyProjectModel>(list);
	   	if (pageNo > pageInfo.getPages()) {
				pageNo = pageInfo.getPages();
				PageHelper.startPage(pageNo, pageSize);
				pageInfo = new PageInfo<MyProjectModel>(list);
			}
	   	model.addAttribute("noRead",messagelist.size()-readedlist.size());
	   	model.addAttribute("pageInfo",pageInfo);
	   	model.addAttribute("typeList",typeList);
	   	model.addAttribute("statusList", statusList);
	   	model.addAttribute("myProject",project);
	   	model.addAttribute("nav","centerli1");
	   	if(1001 == leadingUser.getRoleCode()) {
	   		return "front/center/project-designer";
	   	}
	   	else if(1002 == leadingUser.getRoleCode()) {
	   		return "front/center/project-projectSider";
	   	}
	   	else {
	   		return "front/login";
	   	}
   }
   
   /**
    * 
   * @Author HRX 
   * @Title: tocreatProject
   * @Description: TODO 跳转到新建项目页面
   * @param @param model
   * @param @param req
   * @param @param project
   * @param @param pageNo
   * @param @param pageSize
   * @param @return   
   * @return String    
   * @Date 2019年2月19日 上午11:38:04
   * @throws
    */
   @RequestMapping("/toCreatProject")
   public String toCreatProject(Model model, HttpServletRequest req ,@ModelAttribute ProjectDetail project,
	   		@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
	   		@RequestParam(value = "pageSize", required = false, defaultValue = "7") Integer pageSize) {
	   LeadingUser leadingUser = (LeadingUser)httpSession.getAttribute(FrontConfig.FONT_SESSION);
	   Integer role = leadingUser.getRoleCode();
	   ProjectDetail p = projectManageService.selectByPrimaryKey(project.getId());
	   if(role == 1002){
	 //加载下拉菜单的字典List
	 		model.addAttribute("types", dicProjectTypeService.selectProjectType());
//	 		model.addAttribute("areas",dicService.getAreas());
	 		model.addAttribute("province",dicService.getProvinces());
	 		model.addAttribute("models",dicService.getModels());
	 		model.addAttribute("stages",dicService.getStages());
	 		model.addAttribute("processes",dicService.getProcess());
	 		model.addAttribute("designerType" , dicProjectComponentService.selectProjectComponent());
//	 		model.addAttribute("rate" , myProjectService.selectRateByPrimaryKey(1L));
	 		model.addAttribute("nav","centerli1");
		   List<NotificationManage> messagelist = messageGetService.selectByacceptUser(leadingUser.getTel(),leadingUser.getCreateTime());
		   //查找已读消息
		   List<NotificationView> readedlist = messageGetService.selectreadedMessage(leadingUser.getId(),leadingUser.getCreateTime());
		   model.addAttribute("noRead",messagelist.size()-readedlist.size());
	 		return"/front/center/project-creat";
        }else{
	       return "front/login";
       }
   }


	/**
	 * @Author HRX
	 * @Description //TODO 草稿箱功能
	 * @Date 2019/3/1 15:39
	 * @Param
	 * @return
	 **/
	@ResponseBody
	@RequestMapping("/draft")
	public String draft(Model model,@ModelAttribute ProjectDetail project,@RequestParam(value="designerTypes",required=false) String[] designerTypes
			,@RequestParam(value="files",required=false) MultipartFile[] files,@ModelAttribute MoneyListForm moneyListForm,
						@RequestParam(value="image",required=false) MultipartFile image) {
		myProjectService.draft(project, designerTypes, files, moneyListForm, image);
		return "1";
	}
   
   /**
    * 
   * @Author HRX 
   * @Title: getAreaDic  
   * @Description: TODO 通过省份id依次查询城市和区
   * @param @param who
   * @param @param code
   * @param @return   
   * @return Object[]    
   * @Date 2019年2月19日 上午11:38:30
   * @throws
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
	
	/**
	 * 
	* @Author HRX 
	* @Title: creatProject  
	* @Description: TODO 新建项目
	* @param @return   
	* @return String    
	* @Date 2019年2月19日 下午2:40:58
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/creatProject")
	public String creatProject(@ModelAttribute ProjectDetail project,@RequestParam(value="designerTypes",required=true) String[] designerTypes
	,@RequestParam(value="files",required=false) MultipartFile[] files,@ModelAttribute MoneyListForm moneyListForm,
							   @RequestParam(value="image",required=true) MultipartFile image) {
		myProjectService.creatProject(project, designerTypes, files, moneyListForm, image);
		return "1";
	}
	
	
	@RequestMapping("/toUpdateProject/{id}")
   public String toUpdateProject(Model model, HttpServletRequest req ,@ModelAttribute ProjectDetail project,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "7") Integer pageSize) {
        LeadingUser leadingUser = (LeadingUser)httpSession.getAttribute(FrontConfig.FONT_SESSION);
        Integer role = leadingUser.getRoleCode();
		project = projectManageService.selectByPrimaryKey(project.getId());
        if(role == 1002 && project.getUserId().equals(leadingUser.getId())){
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
            model.addAttribute("money1",moneyList.get(0));
            model.addAttribute("money2",moneyList.get(1));
            model.addAttribute("nav","centerli1");
			List<NotificationManage> messagelist = messageGetService.selectByacceptUser(leadingUser.getTel(),leadingUser.getCreateTime());
			//查找已读消息
			List<NotificationView> readedlist = messageGetService.selectreadedMessage(leadingUser.getId(),leadingUser.getCreateTime());
			model.addAttribute("noRead",messagelist.size()-readedlist.size());
            return"/front/center/project-edit";
        }else{
            return "front/login";
        }
       }
	
	/**
	 * 
	* @Author HRX 
	* @Title: creatProject  
	* @Description: TODO 修改项目
	* @param @return   
	* @return String    
	* @Date 2019年2月19日 下午2:40:58
	* @throws
	 */
	@ResponseBody
	@RequestMapping("/updateProject")
	public String updateProject(@ModelAttribute ProjectDetail project, @RequestParam("files") MultipartFile[] files,
								@ModelAttribute MoneyListForm moneyListForm, @RequestParam(value="designerTypes",required=true) String[] designerTypes,
								@RequestParam(value="image",required=false) MultipartFile image,@RequestParam(value="deleteIds",required=false) String deleteIds) {
		myProjectService.updateProject(project, designerTypes, files, moneyListForm, image,deleteIds);
		return "1";
	}
	
	
	/**
	 * 
	* @Author HRX 
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
	 * 
	* @Author 孟晓冬
	* @Description: 发布按钮和开始建设按钮的处理controller
	* @return String    
	 */
	@ResponseBody
	@RequestMapping("/changeprojectstatus")
	public ResultData changeProjectStatus(
			@RequestParam(value="which",required=true) Integer which,
			@RequestParam(value="projectId",required=true) Long projectId) {
		//which==1需要发布              ==2需要开始建设       ==3需要结项
		/*
		 * 打印入参日志
		 */
		logger.info("调用者为：font/center/project-projectSider，传过来的参数为which=" + which + "projectId = " + projectId);
		
		ResultData resultToPage = new ResultData();
		
		ProjectDetail project = new ProjectDetail();
		project.setId(projectId);
		if(which == 1) {
			project.setProcessCode(1004);
		}else if(which == 2){
			project.setReleaseTime(new Date(System.currentTimeMillis()));
			project.setProcessCode(1005);
			
			// 根据项目id得到项目的信息,以便给设计师发送项目名称等信息。
			ProjectDetail projectDetail = projectManageService.selectByPrimaryKey(projectId);
			String projectName = projectDetail.getName();
			// 调用消息接口
			Long type = 1018L; // 给设计师群发项目开始建设的消息
			List<Application> designers = applicationService.selectAccept(projectId);
			for(int i = 0; i<designers.size();i++){
				String tel = designers.get(i).getTel();
				String userName = designers.get(i).getUsername();
				Map<String,String> para= new HashMap<String,String>();
				para.put("${projectName}",projectName);
				para.put("${userName}",userName);
				messageManageService.allNoticeAdd(tel,type, para);
			}
		}else {
			project.setProcessCode(1006);
			
			// 根据项目id得到项目的信息,以便给设计师发送项目名称等信息。
			ProjectDetail projectDetail = projectManageService.selectByPrimaryKey(projectId);
			String projectName = projectDetail.getName();
			// 调用消息接口
			Long type = 1019L; // 给设计师群发项目结项的消息
			List<Application> designers = applicationService.selectAccept(projectId);
			for(int i = 0; i<designers.size();i++){
				String tel = designers.get(i).getTel();
				String userName = designers.get(i).getUsername();
				Map<String,String> para= new HashMap<String,String>();
				para.put("${projectName}",projectName);
				para.put("${userName}",userName);
				messageManageService.allNoticeAdd(tel,type, para);
			}
		}
		//改变该id的项目状态
		int result = projectManageService.updateByPrimaryKeySelective(project);
		
		//返回结果
		if(result == 1){
			resultToPage.setCode("000000");
			resultToPage.setMessage("操作成功！");
		}
		else{
			resultToPage.setCode("999999");
			resultToPage.setMessage("操作失败！");
		}
		return resultToPage;
	}
   
}
