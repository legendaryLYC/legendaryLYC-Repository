package com.design.background.controller.front;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;


import com.design.background.entity.*;
import com.design.background.service.*;

import com.design.background.common.controller.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.background.config.FrontConfig;
import com.design.background.form.ProjectPayForm;
import com.design.background.mapper.DicStageMapper;
import com.design.background.model.ResultData;


@Controller
@RequestMapping("front/pay")
public class payController extends BaseController {

	@Autowired
	private DicService dicService;
	@Autowired
	LeadingUserProjectorService leadingUserProjectorService;
	@Autowired
	UserAccountService userAccountService;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private RateManagementService  rateManagementService;
	@Autowired
	private ProjectPhaseAuditService projectPhaseAuditService;
	
    private static final Logger logger = LoggerFactory.getLogger(payController.class);

	
/*
 * 
    @RequestMapping("/topay")
    public String toPay(Model model
    		//,@RequestParam("projectId") int projectId
    		) 
    {
    	int projectId=2;
    	//获取费率
    	DicDefaultRate dicdefaultRate=dicService.selectRateByPrimaryKey((long) 1);
    	model.addAttribute("rate", dicdefaultRate);
    	//根据项目id获取项目详情
    	ProjectDetail projectDetail=dicService.selectProjectByPrimaryKey((long)projectId);
    	model.addAttribute("projectDetail", projectDetail);
    	//存储项目阶段编码
    	String stage=null;
    	if(projectDetail.getStageCode()==1000) {
    		stage="方案阶段";
    	}
    	if(projectDetail.getStageCode()==1001) {
    		stage="施工图阶段";
    	}
    	//存储施工阶段
    	model.addAttribute("stage", stage);
    	//获取项目的设计总预算
    	model.addAttribute("design_budget",projectDetail.getDesignBudget() );
    	//获取项目中的设计师
    	List<ProjectPayForm> projectPayForms=leadingUserProjectorService.selectprojectdetails((long)projectId);
    	for(ProjectPayForm pro : projectPayForms) {
  		  		if(pro.getTypeCode()==1000) { // 建筑
  		  			//存储userid和balance
  		  			model.addAttribute("buildingUserId", pro.getUserId());
  		  			model.addAttribute("buildingBalance", pro.getBalance());
  		  		}
  		  		if(pro.getTypeCode()==1001) { // 结构
  		  		model.addAttribute("structureUserId", pro.getUserId());
		  			model.addAttribute("structureBalance", pro.getBalance());
  		  		} 
  		  		if(pro.getTypeCode()==1002) { // 水
  		  		model.addAttribute("waterUserId", pro.getUserId());
		  			model.addAttribute("waterBalance", pro.getBalance());
  		  		}
  		  		if(pro.getTypeCode()==1003) { // 电
  		  		model.addAttribute("electricityUserId", pro.getUserId());
		  			model.addAttribute("electricityBalance", pro.getBalance());
  		  		}
  		  		if(pro.getTypeCode()==1004) { // 暖
  		  		    model.addAttribute("warmUserId", pro.getUserId());
		  			model.addAttribute("warmBalance", pro.getBalance());
  		  		}
    		}
    	
    	//System.out.println(pro[0].);
    	System.out.println(projectPayForms.size());
		return "front/pay";
    	//return "front/Totest";
    }
    */
	
	
	
	
	
	 @RequestMapping("/topay")
	    public String toPay(Model model
	    		,@RequestParam("id") Integer id
	    		//,@RequestParam("stageCode") int stageCode
	    		) 
	    {
	    	// 获取费率
	    	DicDefaultRate dicdefaultRate=dicService.selectRateByPrimaryKey((long) 1);
	    	model.addAttribute("rate", dicdefaultRate);
	    	
	    	// 根据项目id获取项目详情
	    	ProjectDetail projectDetail=dicService.selectProjectByPrimaryKey((long)id);
	    	model.addAttribute("projectDetail", projectDetail);


	    	// 获取项目各类设计师费用
			MoneyDistribution MoneyDistribution =new MoneyDistribution();
			MoneyDistribution.setProjectId((long)id);
			MoneyDistribution.setStageCode(projectDetail.getStageCode());
	    	MoneyDistribution money=dicService.findbyStage(MoneyDistribution);
	    	model.addAttribute("money", money);
	    //	System.out.println(money.getWaterMoney());
	    	// 获取项目的设计总预算
	    	model.addAttribute("design_budget",projectDetail.getDesignBudget() );


	    	// 获取项目中的设计师
	    	List<ProjectPayForm> projectPayForms=leadingUserProjectorService.selectprojectdetails((long)id);
	    	for(ProjectPayForm pro : projectPayForms) {
	  		  		if(pro.getTypeCode()==1000) { // 建筑
	  		  			// 存储userid和balance
	  		  			model.addAttribute("buildingUserId", pro.getUserId());
	  		  			model.addAttribute("buildingBalance", pro.getBalance());
	  		  		}
	  		  		if(pro.getTypeCode()==1001) { // 结构
	  		  		    model.addAttribute("structureUserId", pro.getUserId());
			  			model.addAttribute("structureBalance", pro.getBalance());
			  			System.out.println("结构零钱"+pro.getBalance());
			  			System.out.println("结构id:"+pro.getUserId());
			  			
	  		  		} 
	  		  		if(pro.getTypeCode()==1002) { // 水
	  		  		model.addAttribute("waterUserId", pro.getUserId());
			  			model.addAttribute("waterBalance", pro.getBalance());
			  			
	  		  		}
	  		  		if(pro.getTypeCode()==1003) { // 电
	  		  			model.addAttribute("electricityUserId", pro.getUserId());
	  		  			
			  			model.addAttribute("electricityBalance", pro.getBalance());
	  		  		}
	  		  		if(pro.getTypeCode()==1004) { // 暖
	  		  		    model.addAttribute("warmUserId", pro.getUserId());
			  			model.addAttribute("warmBalance", pro.getBalance());
	  		  		}
	    		}
	    	System.out.println("获取到的设计师位数："+projectPayForms.size());
			return "front/pay";
	    	//return "front/Totest";
	    }
	
	
	
	
	
    /**
     * 支付项目
     * @param model
     * @param projectId
     * @return
     */
    @ResponseBody
    @RequestMapping("/payforproject")
    public ResultData PayForProject(Model model ,@RequestParam("projectId") Long projectId,
    		@RequestParam(value= "warm_money",required = false,defaultValue="0") BigDecimal warm_money,
    		@RequestParam(value="water_money",required = false,defaultValue="0") BigDecimal water_money,
    		@RequestParam(value="building_money",required = false,defaultValue="0") BigDecimal building_money,
    		@RequestParam(value="electricity_money",required = false,defaultValue="0") BigDecimal electricity_money,
    		@RequestParam(value="structure_money",required = false,defaultValue="0") BigDecimal structure_money,
    		@RequestParam(value="sum") BigDecimal sum,
    		@RequestParam(value="waterUserId",required = false) Long waterUserId,
    		@RequestParam(value="warmUserId",required = false) Long warmUserId,
    		@RequestParam(value="buildingUserId",required = false) Long buildingUserId,
    		@RequestParam(value="electricityUserId",required = false) Long electricityUserId,
    		@RequestParam(value="structureUserId",required = false) Long structureUserId
    		) {
		// 获取费率
		Long rateid=1L;
		RateManagement rate=rateManagementService.selectByPrimaryKey(rateid);
		BigDecimal price=(new BigDecimal(1.00)).subtract(rate.getPrice());
    	MoneyDistribution moneyDistribution=null;
    	ResultData resultData=new ResultData();
    	resultData.setCode("999999");
    	System.out.println("这里没有问题");
    	// 给不同的设计师存钱，前台回传设计师列表以及对应的薪酬，根据设计师id去存钱。并将每一个设计师的钱存起来。
    	if(water_money!=null&&water_money.compareTo(BigDecimal.ZERO)==1) // 有钱
    	{
    		if(waterUserId != null){
				UserAccount user1=userAccountService.selectuserByUserId(waterUserId);
				//更新

				user1.setBalance(water_money.add(user1.getBalance().multiply(price)));
				userAccountService.updateBalance(user1);
			}
    	}
    	if(warm_money!=null&&warm_money.compareTo(BigDecimal.ZERO)==1) // 有钱
    	{
			if(warmUserId != null){
				UserAccount user2= userAccountService.selectuserByUserId(warmUserId);
				//更新
				user2.setBalance(warm_money.add(user2.getBalance().multiply(price)));
				userAccountService.updateBalance(user2);
			}

    		
    	}
    	if(building_money!=null&&building_money.compareTo(BigDecimal.ZERO)==1) // 有钱
    	{
			if(buildingUserId != null){
				UserAccount user3= userAccountService.selectuserByUserId(buildingUserId);
				//更新
				user3.setBalance(building_money.add(user3.getBalance().multiply(price)));
				userAccountService.updateBalance(user3);
			}
    	}
    	if(electricity_money.compareTo(BigDecimal.ZERO)==1) // 有钱
    	{
			if(electricityUserId != null){
				UserAccount user4= userAccountService.selectuserByUserId(electricityUserId);
				//更新
				user4.setBalance(electricity_money.add(user4.getBalance().multiply(price)));
				userAccountService.updateBalance(user4);
			}
    	}
    	if(structure_money!=null&&structure_money.compareTo(BigDecimal.ZERO)==1) // 有钱
    	{
    		if(structureUserId != null){
				UserAccount user5=userAccountService.selectuserByUserId(structureUserId);
				//更新
				user5.setBalance(structure_money.add(user5.getBalance().multiply(price)));
				userAccountService.updateBalance(user5);
			}
    	}

//    	// 给项目方扣钱，用session中的userid
//    	LeadingUser leadingUser=null;
//        leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
//    	// UserAccount userAccount = userAccountService.selectByUserId((long)leadingUser.getId());
//		System.out.println(leadingUser.getId());
//        UserAccount userAccount = userAccountService.selectuserByUserId((Long)leadingUser.getId());
//       
//    	sum=userAccount.getBalance().subtract(sum);  
//    	// 判断钱是否够
//    	if(sum.compareTo(BigDecimal.ZERO)==1) {
//    		userAccount.setBalance(sum);
//    		//更新余额
//        	int key=userAccountService.updateBalance(userAccount);
        	projectPhaseAuditService.projectorThroughApplication(projectId);
//        	if(key==1) {	
//        		resultData.setCode("000000");
//        	}
//        	
//    	}
//    	else {
//    		resultData.setCode("999999");
//    		resultData.setMessage("余额不足");
//    	}
    	resultData.setCode("000000");
		return resultData;
    }

}
