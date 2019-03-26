package com.design.background.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.background.entity.BankCard;
import com.design.background.entity.CapitalAudit;
import com.design.background.entity.UserAccount;
import com.design.background.form.MyAccountForm;
import com.design.background.form.TransactionForm;
import com.design.background.mapper.BankCardMapper;
import com.design.background.mapper.CapitalAuditMapper;
import com.design.background.mapper.LeadingUserMapper;
import com.design.background.mapper.TransactionMapper;
import com.design.background.mapper.UserAccountMapper;
import com.design.background.service.UserAccountService;
@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {
	
	@Autowired
	private UserAccountMapper userAccountMapper;
	@Autowired 
	private LeadingUserMapper leadingUserMapper;
	@Autowired 
	private BankCardMapper bankCardMapper;
	@Autowired 
	private TransactionMapper transactionMapper;
	@Autowired 
	private CapitalAuditMapper capitalAuditMapper;

	@Override
	public MyAccountForm selectById(Long Id) {
		// TODO Auto-generated method stub
		return leadingUserMapper.selectById(Id);
	}


	@Override
	public List<BankCard> selectByUserId(Long userId) {
		// TODO Auto-generated method stub
		return bankCardMapper.selectByUserId(userId);
	}


	@Override
	public List<TransactionForm> selectTran(Long userId) {
		
		return transactionMapper.selectTran(userId);
	}


	@Override
	public boolean deleteCard(Long id) {
		// TODO Auto-generated method stub
		return bankCardMapper.deleteById(id);
	}


	@Override
	public boolean insertCard(BankCard record) {
		// TODO Auto-generated method stub
		return bankCardMapper.insertCard(record);
	}


	@Override
	public BankCard selectByBankCardId(Long id) {
		// TODO Auto-generated method stub
		return bankCardMapper.selectByPrimaryKey(id);
	}


	@Override
	public int insertCapitalAudit(CapitalAudit record) {
		// TODO Auto-generated method stub
		return capitalAuditMapper.insert(record);
	}


	@Override
	public int updateBalance(UserAccount record) {
		// TODO Auto-generated method stub  
		return userAccountMapper.update(record);
	}


	@Override
	public UserAccount selectBalanceByPrimaryKey(long id) {
		// TODO Auto-generated method stub
		return userAccountMapper.selectByUserId(id);
	}
	@Override
	public UserAccount findByUserId(long id) {
		return userAccountMapper.findByUserId(id);
	}
/**
 * 根据项目id查询
 */
	@Override
	public UserAccount selectuserByUserId(Long userId) {
		// TODO Auto-generated method stub

		return 	userAccountMapper.selectByUserId(userId);
	}

}
