package com.design.background.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.design.background.entity.Transaction;
import com.design.background.entity.UserAccount;
import com.design.background.form.ProjectForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.entity.CapitalAudit;
import com.design.background.entity.DicProjectFee;
import com.design.background.mapper.CapitalAuditMapper;
import com.design.background.service.CapitalAuditService;
import com.design.background.service.UserAccountService;

@Service("capitalAuditService")
public class CapitalAuditServiceImpl implements CapitalAuditService {

	@Autowired
	private CapitalAuditMapper capitalAuditMapper;
	@Autowired
	private UserAccountService userAccountService;
	
	private static final Logger logger = LoggerFactory.getLogger(CapitalAuditServiceImpl.class);
	
	/**
	 * 查询所有
	 * 
	 */
	@Override
	public List<CapitalAudit> selectAll() {
		
		return capitalAuditMapper.selectAll();
	}

	@Override
	public List<CapitalAudit> select(CapitalAudit capital) {
		// TODO Auto-generated method stub
		List<CapitalAudit> capitalAudit = null;
		try {
			capitalAudit = capitalAuditMapper.select(capital);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return capitalAudit;
	}

	@Override
	public CapitalAudit selectById(Long id) {
		// TODO Auto-generated method stub
		return capitalAuditMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(CapitalAudit record) {
		// TODO Auto-generated method stub
		return capitalAuditMapper.updateByPrimaryKey(record);
	}

	@Override
	public int update(CapitalAudit record) {
		// TODO Auto-generated method stub
		return capitalAuditMapper.update(record);
	}

	@Override
	public int insertSelective(Transaction transaction) {
		// TODO Auto-generated method stub
		return capitalAuditMapper.insertSelective(transaction);
	}

	@Override
	public Boolean applicationApproved(Long auditId) {
		
		CapitalAudit audit = null;
		UserAccount userAccount = null;
		try {
			audit = capitalAuditMapper.selectByPrimaryKey(auditId);
			userAccount = userAccountService.selectuserByUserId(audit.getApplicantId());
		} catch (Exception e) {
			logger.error("根据id查询资金审核项或根据用户id查询useraccount失败!"+e.getMessage());
		}
		//改变余额
		if("提现".equals(audit.getApplicationType())) {
			//提现之后的余额
			BigDecimal newBalance = userAccount.getBalance().subtract(audit.getMoney());
			//提现之后的提现总金额记录
			BigDecimal newExtract = userAccount.getExtract().add(audit.getMoney());
			userAccount.setBalance(newBalance);
			userAccount.setExtract(newExtract);
			int flag = 0;
	    	flag = userAccountService.updateBalance(userAccount);
	    	if(flag == 1) {
	    		return true;
	    	}else {
	    		return false;
	    	}
		}else {
			return false;
		}
	}
	

	
}
