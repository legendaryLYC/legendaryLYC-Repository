package com.design.background.model;

public class StatisticalSearchUserModel{
	
	private int roleCode;

	private int num;
	private int week0;
	private int week1;
	private int week2;
	private int week3;
	private int week4;
	private int week5;
	private int week6;

	public int getWeek0() {
		return week0;
	}

	public void setWeek0(int week0) {
		this.week0 = week0;
	}

	public int getWeek1() {
		return week1;
	}

	public void setWeek1(int week1) {
		this.week1 = week1;
	}

	public int getWeek2() {
		return week2;
	}

	public void setWeek2(int week2) {
		this.week2 = week2;
	}

	public int getWeek3() {
		return week3;
	}

	public void setWeek3(int week3) {
		this.week3 = week3;
	}

	public int getWeek4() {
		return week4;
	}

	public void setWeek4(int week4) {
		this.week4 = week4;
	}

	public int getWeek5() {
		return week5;
	}

	public void setWeek5(int week5) {
		this.week5 = week5;
	}

	public int getWeek6() {
		return week6;
	}

	public void setWeek6(int week6) {
		this.week6 = week6;
	}

	public int getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(int roleCode) {
		this.roleCode = roleCode;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public StatisticalSearchUserModel(int roleCode, int num) {
		super();
		this.roleCode = roleCode;
		this.num = num;
	}

	public StatisticalSearchUserModel() {
		super();
	}
	
}
