package com.design.background.controller.admin;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.design.background.entity.Transaction;
import com.design.background.entity.UserAccount;
import com.design.background.service.TransactionService;
import com.design.background.service.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.design.background.entity.CapitalAudit;

import com.design.background.service.CapitalAuditService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
*  王喜壮
* 资金审核
*/
@Controller
@RequestMapping("/admin/capitalAudit")
public class CapitalAuditController {
	
	    private static final Logger logger = LoggerFactory.getLogger(CapitalAuditController.class);

	    @Autowired
        private CapitalAuditService capitalAuditService;
		@Autowired
		private UserAccountService userAccountService;

	    @Autowired
		TransactionService transactionService;
	   
	    @RequestMapping(value = { "", "/" })
		public String selectProjectFee(Model model,
				@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
				@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
				CapitalAudit capital) {
			PageHelper.startPage(pageNo, pageSize);
			List<CapitalAudit> capitalAudit = capitalAuditService.select(capital);
			PageInfo<CapitalAudit> pageInfo = new PageInfo<CapitalAudit>(capitalAudit);
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("capital", capital);
			return "admin/CapitalAudit/capitalAuditlist";
		}


		/**
		 * 通过申请
		 * 
		 * @param model
		 * @param user
		 * @return
		 * @Description:
		 */

		@ResponseBody
		@RequestMapping(value = "/examine/{id}")
		public String availableUser(Model model, @PathVariable("id") Long id) {
			
			CapitalAudit capitalAudit = capitalAuditService.selectById(id);
			//申请人id
			Long ApplicantId = capitalAudit.getApplicantId();
			//改变余额
			Boolean result = capitalAuditService.applicationApproved(capitalAudit.getId());
			
			//插入记录
			Transaction transaction = new Transaction();
			transaction.setContent(capitalAudit.getApplicationType());
			transaction.setMoney(capitalAudit.getMoney());
			transaction.setUserId(capitalAudit.getApplicantId());
			Date time = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String create = sdf.format(time);
			Date createtime = new Date();
			try {
				createtime = sdf.parse(create);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			transaction.setCreatTime(createtime);
			//交易记录
			transactionService.insertSelective(transaction);
		
			if ("待审核".equals(capitalAudit.getState())) {
				capitalAudit.setState("通过");
                int flag = capitalAuditService.updateById(capitalAudit);
                
                if( flag == 1 && result == true) {
                	return "1";
                }else {
                	return "0";
                }
			}
			return "0";
		}
		
		// 多选通过
		@ResponseBody
		@RequestMapping(value = "/examineAll", produces = "application/json", consumes = "application/json")
		public Integer deleteAll(@RequestBody Long[] ids) {
			for (long id : ids) {
//				System.out.println(id);
			}
			try {
				for (long id : ids) {
					CapitalAudit capitalAudit = capitalAuditService.selectById(id);


					Transaction transaction = new Transaction();
					transaction.setContent(capitalAudit.getApplicationType());
					transaction.setMoney(capitalAudit.getMoney());
					transaction.setUserId(capitalAudit.getApplicantId());
					Date time = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String create = sdf.format(time);
					Date createtime = new Date();
					try {
						createtime = sdf.parse(create);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					transaction.setCreatTime(createtime);
					transactionService.insertSelective(transaction);

					capitalAudit.setState("通过");
					capitalAuditService.updateById(capitalAudit);
				}
				return 1;
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return 0;
		}
		// 进入拒绝页面
		@RequestMapping(value = "/edit/{id}")
		public String updatebyidBefore(Model model, @ModelAttribute(value = "CapitalAudit") CapitalAudit capitalAudit,
				@PathVariable("id") Long id) {
			capitalAudit = capitalAuditService.selectById(id);
			model.addAttribute("capitalAudit", capitalAudit);
			return "admin/CapitalAudit/capitalAudit-edit";
		}
		
		@ResponseBody
		@RequestMapping(value = "/edit")
		public String updatebyid(Model model, @ModelAttribute(value = "capitalAudit") CapitalAudit capitalAudit,HttpServletRequest request) {
			logger.debug("调用productRepair/edit");
			
				Long id = capitalAudit.getId();
				System.out.println(id);
				String reason = capitalAudit.getReason();
				CapitalAudit result = new CapitalAudit();
				result.setId(id);
				result.setState("拒绝");
				result.setReason(reason);
				CapitalAudit auit = capitalAuditService.selectById(id);

				int flag = capitalAuditService.update(result);
				if(flag == 1) {
					return "1";
				}else {
					return "0";
				}  
		
			}
		

}
