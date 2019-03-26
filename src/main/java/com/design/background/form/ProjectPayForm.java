package com.design.background.form;

import java.math.BigDecimal;

public class ProjectPayForm {

	  private Long id;
	  private Long userId;
	  private int typeCode;
	  private BigDecimal balance;
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public int getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
