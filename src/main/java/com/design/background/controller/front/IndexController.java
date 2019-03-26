package com.design.background.controller.front;

import com.design.background.common.controller.BaseController;
import com.design.background.config.Constant;
import com.design.background.config.FrontConfig;

import com.design.background.entity.*;

import com.design.background.form.IndexPictureForm;
import com.design.background.form.ProjectorForm;
import com.design.background.model.AreaModel;
import com.design.background.model.ResultData;

import com.design.background.service.*;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: design-background
 * @description: 前台首页访问
 * @author: 王震
 * @create: 2019-02-15 10:20
 **/
@Controller
@RequestMapping("/")
public class IndexController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private ExampleProjectService exampleProjectService;

	@Autowired
	private MyProjectService myProjectService;
	@Autowired
	private LeadingUserProjectorService leadingUserProjectorService;
	@Autowired
	private DicProjectTypeService projectTypeService;

	@Autowired
	private DicProjectStatusService projectStatusService;
	@Autowired
	private DicService  dicService;
	@Autowired
	private CompanyManagementService companyManagementService;
	@Autowired
	private DesignerTypeExtendService designerTypeExtendService;

	@Autowired
	private AdvertManageService advertManageService;
	@Autowired
	private HttpSession httpSession;

	@Autowired
	private MessageGetService messageGetService;


	@RequestMapping("/")
	public String welcome(Model model, HttpServletRequest req) {
		model.addAttribute("nav","li1");
		/**
		 * 修改为使用PageHelper插件进行分页查询，解决多limit问题
		 */
		PageHelper.startPage(0,6);
		List<ExampleProject> exampleList = exampleProjectService.selectSomeExample();

		//获取所有位置被选中的广告信息
		List<AdvertisingManagement> rotationList = advertManageService.getSelectedLocations();
		// 获取六个轮播图的url
		model.addAttribute("rotationList",rotationList);
		// 获取展示的案例信息
		model.addAttribute("exampleList",exampleList);
		return "front/index";
	}

	/**跳转到登录页面
	 * @author：任松
	 * @return
	 */
	@RequestMapping("/login")
	public String toLoginPage() {
		return "front/login";
	}
	/**跳转到个人信息页面
	 * @author：任松
	 * @return
	 */
	@RequestMapping("/center/information")
	public String toInformation(Model model) {
		//获取用户信息
		LeadingUser leadingUser=new LeadingUser();
		leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
		// 存储该用户信息
		model.addAttribute("leadingUser", leadingUser);
		model.addAttribute("nav","centerli2");
		model.addAttribute("tel", leadingUser.getTel());
		//获取省份列表
		// 根据id获取城市列表和地区列表
		//model.addAttribute("area",dicService.getAreaByCityId(id));
		//model.addAttribute("city",dicService.getCitysByProvinceId(leadingUser.getId()));
		String address="暂未选择";
		String introduction="暂未填写";


		String acceptUser = leadingUser.getTel();
		//查找全部消息
		List<NotificationManage> messagelist = messageGetService.selectByacceptUser(acceptUser,leadingUser.getCreateTime());
		//查找已读消息
		List<NotificationView> readedlist = messageGetService.selectreadedMessage(leadingUser.getId(),leadingUser.getCreateTime());


		AreaModel addr = leadingUserProjectorService.selectAreaModelByUserId(leadingUser.getId());
		if(addr != null && addr.getProvinceId() != null) {
			address = addr.getProvinceName() + " " +
					( addr.getCityName() == null ? "" :  addr.getCityName() ) + " " +
					( addr.getAreaName() == null ? "" :  addr.getAreaName() );
		}

//    	if(leadingUser.getArea()!=null)
//    	{
//    		ProjectorForm projectorForm=leadingUserProjectorService.selectUserAreaById(leadingUser.getId(), leadingUser.getArea());
//    		address=projectorForm.getProvincename()+" "+projectorForm.getCityname()+" "+projectorForm.getAreaname();
//        	model.addAttribute("projectorForm",projectorForm);
//    	}
		if(leadingUser.getIntroduction()!=null) {
			introduction=leadingUser.getIntroduction();
		}
		String type="暂未选择";
		if(leadingUser.getRoleCode()==1001) { // 如果是设计师，则获取设计师种类
			//获取设计师种类
			ArrayList code=designerTypeExtendService.getResult(leadingUser.getId());
			int length=code.size();
			for(int i=0;i<length;i++) {
				if((int)code.get(i)==1000) {
					model.addAttribute("building", "1");
					if(type=="暂未选择")
					{   type="";
						type+="建筑 ";
					}else {
						type+="建筑 ";
					}
				}
				if((int)code.get(i)==1001) {
					model.addAttribute("structure", "1");
					if(type=="暂未选择")
					{   type="";
						type+="结构";
					}else {
						type+="结构";
					}
				}
				if((int)code.get(i)==1002) {
					model.addAttribute("water", "1");
					if(type=="暂未选择")
					{   type="";
						type+="水 ";
					}else {
						type+="水 ";
					}
				}
				if((int)code.get(i)==1003) {
					model.addAttribute("electricity", "1");
					if(type=="暂未选择")
					{	type="";
						type+="电 ";
					}else {
						type+="电";
					}
				}
				if((int)code.get(i)==1004) {
					model.addAttribute("warm", "1");
					if(type=="暂未选择")
					{	type="";
						type+="暖 ";
					}else {
						type+="暖";
					}
				}
			}
		}
		model.addAttribute("noRead",messagelist.size()-readedlist.size());
		model.addAttribute("type",type);
		model.addAttribute("introduction", introduction);
		model.addAttribute("address",address);
		model.addAttribute("provinces",dicService.getProvinces());
		return "front/center/information";
	}
	@ResponseBody
	@RequestMapping("/isLogin")
	public ResultData isLogin()
	{
		LeadingUser leadingUser=new LeadingUser();
		leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
		return leadingUser == null?new ResultData(Constant.UNLOGIN,"尚未登录"):new ResultData(Constant.LOGIN,"已登录");
	}

	@RequestMapping("/toProtool")
	public String toProtool() {
		return "front/protool";
	}


}
