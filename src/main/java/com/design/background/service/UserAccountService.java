package com.design.background.service;

import java.util.List;

import com.design.background.entity.BankCard;
import com.design.background.entity.CapitalAudit;
import com.design.background.entity.UserAccount;
import com.design.background.form.MyAccountForm;
import com.design.background.form.TransactionForm;


public interface UserAccountService {
	//查询银行卡
	List<BankCard> selectByUserId(Long userId);
	UserAccount selectuserByUserId(Long userId);
	// 查询单条银行卡信息
	BankCard selectByBankCardId(Long id);
	// 查询用户信息
	MyAccountForm selectById(Long Id);
	// 查询交易记录
	List<TransactionForm> selectTran(Long userId);
	// 删除银行卡	
	boolean deleteCard(Long id);
	// 添加银行卡
	boolean insertCard(BankCard rencd);
	// 插入用户提交的提现申请
	int insertCapitalAudit(CapitalAudit record);
	// 修改余额
	int updateBalance(UserAccount record);
	//查询余额
	UserAccount selectBalanceByPrimaryKey(long id);
	//根据体现表id查询 金额
	UserAccount findByUserId(long id);
}
