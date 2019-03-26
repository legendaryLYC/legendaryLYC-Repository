package com.design.background.model;

import java.math.BigDecimal;

public class StatisticalSearchUserCountModel {

	private String username = "";
	
	private BigDecimal balance = new BigDecimal("0");

	private int num = 0;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
