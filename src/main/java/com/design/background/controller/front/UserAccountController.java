package com.design.background.controller.front;

import com.design.background.common.controller.BaseController;
import com.design.background.config.FrontConfig;
import com.design.background.controller.admin.DicProjectComponentController;
import com.design.background.entity.*;
import com.design.background.form.MyAccountForm;
import com.design.background.form.TransactionForm;
import com.design.background.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/myAccount")
public class UserAccountController extends BaseController {

		private static final Logger logger = LoggerFactory.getLogger(DicProjectComponentController.class);
		@Autowired
		private LeadingUserProjectorService leadingUserProjectorService;
		@Autowired
		private UserAccountService userAccountService;
		@Autowired
		private HttpSession httpSession;
		@Autowired
		private MessageGetService messageGetService;
		@Autowired
		private MessageManageService messageManageService;
		@Autowired
		private ProjectDetailService projectDetailService;
		
		// 进入我的账户
	    @RequestMapping("/inwallet")
	    public String select(Model model, HttpServletRequest req,@RequestParam(required = false) String defaultTable) {
	    	
			LeadingUser leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
	    	Long id = leadingUser.getId();
			String acceptUser = leadingUser.getTel();

			//查找全部消息
			List<NotificationManage> messagelist = messageGetService.selectByacceptUser(acceptUser,leadingUser.getCreateTime());
			//查找已读消息
			List<NotificationView> readedlist = messageGetService.selectreadedMessage(leadingUser.getId(),leadingUser.getCreateTime());

	    	// 根据用户id查询我的账户信息
	    	MyAccountForm myAccountForm = userAccountService.selectById(id);
	    	System.out.println(myAccountForm);
	    	BigDecimal payAmount = myAccountForm.getPayAmount();
	    	BigDecimal balance = myAccountForm.getBalance();
	    	BigDecimal extract = myAccountForm.getExtract();
	    	BigDecimal allMoney = payAmount == null ? payAmount : payAmount.add(balance);
	    	System.out.println(allMoney);
	    	model.addAttribute("allMoney",allMoney);
	    	model.addAttribute("myAccountForm",myAccountForm);
	    	model.addAttribute("defaultTable",defaultTable);
	    	// 从上个查询获取的userId 查询银行卡信息
	    	Long userId = myAccountForm.getUserId();
	    	List<BankCard> bankCardList = userAccountService.selectByUserId(userId);
	    	model.addAttribute("bankCardList",bankCardList);
	    	//根据userId查询用户交易记录
	    	List<TransactionForm> transactionForm = userAccountService.selectTran(userId);
	    	model.addAttribute("transactionForm",transactionForm);
			model.addAttribute("nav","centerli4");
			model.addAttribute("noRead",messagelist.size()-readedlist.size());
	    	return "front/center/wallet";
	    }
	    // 提现
	    @Transactional
	    @ResponseBody
	    @RequestMapping("/extract")
	    public String Extract(Model model,
	    		@RequestParam(value = "money",required = false) BigDecimal money,
	    		@RequestParam(value = "id1",required = false) Long id1) {
	    	// 设置日期格式,为获取当前系统时间
	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			LeadingUser leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
	    	Long id = leadingUser.getId();
	    	// 查询银行卡卡号
	    	BankCard bankCard = new BankCard();
	    	bankCard = userAccountService.selectByBankCardId(id1);
	    	// 查询用户信息
	    	MyAccountForm myAccountForm = new MyAccountForm();
	    	myAccountForm = userAccountService.selectById(id);
	    	if(myAccountForm.getBalance() == null){
				return "2";
			}
	    	// 插入提现申请
	    	if(money == null || id1 == null ) {
	    		return "3";
	    	}
			int result = 0;
	    	if(myAccountForm.getBalance() != null){
				BigDecimal balance = myAccountForm.getBalance() == null ? null : myAccountForm.getBalance().subtract(money);
				result = balance.compareTo(BigDecimal.ZERO);//比较提现后的余额是否大于0
			}
	    	if(result>=0) {
		    	CapitalAudit capitalAudit = new CapitalAudit();
		    	capitalAudit.setApplicantName(myAccountForm.getName());
		    	capitalAudit.setMoney(money);
		    	capitalAudit.setApplicationType("提现");
		    	capitalAudit.setCard(bankCard.getAccount());
		    	capitalAudit.setTel(myAccountForm.getTel());
		    	capitalAudit.setApplicationTime(df.format(new Date()));
		    	capitalAudit.setApplicantId(id);
		    	capitalAudit.setState("待审核");
		    	int flag = 0;
		    	flag = userAccountService.insertCapitalAudit(capitalAudit);
				Long type = 1008L;
				Map<String,String> para= new HashMap();
				boolean messageResult = messageManageService.allNoticeAdd(leadingUser.getTel(),type, para);
//		    	System.out.println(flag);
//		    	// 修改余额
//		    	UserAccount userAccount = new UserAccount();
//				BigDecimal finallyBalance = null;
//				BigDecimal finallyExtract = null;
//		    	if(myAccountForm.getBalance() != null){
//					finallyBalance = myAccountForm.getBalance().subtract(money);
//					finallyExtract = myAccountForm.getExtract().add(money);
//				}
//		    	System.out.println(myAccountForm.getBalance());
//		    	System.out.println(finallyBalance);
//		    	System.out.println(finallyExtract);
//		    	userAccount.setUserId(myAccountForm.getUserId());
//		    	userAccount.setBalance(finallyBalance);
//		    	userAccount.setExtract(finallyExtract);
//		    	int flag1 = 0;
//		    	flag1 = userAccountService.updateBalance(userAccount);
//		    	System.out.println(flag1+"=====================");
		    	
		    	
		    	if(flag == 1) {
		    		return "1";
		    	}else {
		    		return "0";
		    	}
	    	}else {
	    		return "2";
	    	}
	    }
	    
	    // 删除银行卡
	    @RequestMapping("/deleteCard/{id}")
		@ResponseBody
		public String deleteCard(@ModelAttribute BankCard bankCard){
	    	System.out.println(bankCard.getId());
			String result="0";
			boolean flag=false;
			
			flag = userAccountService.deleteCard(bankCard.getId());
			System.out.println(flag);
			
			if(flag=true) {
				result="1";
			}
			return result;
		}
	    
	    // 前往添加页
	    @RequestMapping(value = "/addCard")
		public String addRoleBefore(Model model) {
			return "front/center/addCard";
		}
	    
	    // 添加銀行卡
	 		@ResponseBody
	 		@RequestMapping(value = "/insertCard")
	 		public String insertbrand(Model model,
	 				@RequestParam(value = "name", required = false, defaultValue="") String name,
	 				@RequestParam(value = "account", required = false, defaultValue="") String account,
	 				@RequestParam(value = "idCard", required = false, defaultValue="") String idCard,
	 				@RequestParam(value = "tel", required = false, defaultValue="") String tel,
	 				@RequestParam(value = "cardType", required = false, defaultValue="") String cardType){
				LeadingUser leadingUser = (LeadingUser) httpSession.getAttribute(FrontConfig.FONT_SESSION);
	 			Long userId = leadingUser.getId();
	 			BankCard bankCard = new BankCard();
	 			bankCard.setUserId(userId);
	 			bankCard.setName(name);
	 			bankCard.setAccount(account);
	 			bankCard.setIdCard(idCard);
	 			bankCard.setTel(tel);
	 			System.out.println(tel);
	 			bankCard.setCardType(cardType);
	 			
	 			boolean flag = userAccountService.insertCard(bankCard);
	 				if(flag==true) {
	 					return "1"; // 插入成功
	 			}
	 			return "0";
	 		}
}
