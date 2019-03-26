package com.design.background.model;

import java.util.List;

public class WeekAndLastWeekModel {
	
	// 上周的 StatisticalSearchUserModel 数据
	private List<StatisticalSearchUserModel> lastWeekMon;
	
	private List<StatisticalSearchUserModel> lastWeekTues;
	
	private List<StatisticalSearchUserModel> lastWeekWed;
	
	private List<StatisticalSearchUserModel> lastWeekThurs;
	
	private List<StatisticalSearchUserModel> lastWeekFri;
	
	private List<StatisticalSearchUserModel> lastWeekSat;

	private List<StatisticalSearchUserModel> lastWeekSun;
	
	// 本周的 StatisticalSearchUserModel 数据
	private List<StatisticalSearchUserModel> thisWeekMon;
	
	private List<StatisticalSearchUserModel> thisWeekTues;
	
	private List<StatisticalSearchUserModel> thisWeekWed;
	
	private List<StatisticalSearchUserModel> thisWeekThurs;
	
	private List<StatisticalSearchUserModel> thisWeekFri;
	
	private List<StatisticalSearchUserModel> thisWeekSat;
	
	private List<StatisticalSearchUserModel> thisWeekSun;
	
	private int whatThisDay = 0; // 今天是星期几与星期一的差，星期一就为0，星期五就为4

	public List<StatisticalSearchUserModel> getLastWeekMon() {
		return lastWeekMon;
	}

	public void setLastWeekMon(List<StatisticalSearchUserModel> lastWeekMon) {
		this.lastWeekMon = lastWeekMon;
	}

	public List<StatisticalSearchUserModel> getLastWeekTues() {
		return lastWeekTues;
	}

	public void setLastWeekTues(List<StatisticalSearchUserModel> lastWeekTues) {
		this.lastWeekTues = lastWeekTues;
	}

	public List<StatisticalSearchUserModel> getLastWeekWed() {
		return lastWeekWed;
	}

	public void setLastWeekWed(List<StatisticalSearchUserModel> lastWeekWed) {
		this.lastWeekWed = lastWeekWed;
	}

	public List<StatisticalSearchUserModel> getLastWeekThurs() {
		return lastWeekThurs;
	}

	public void setLastWeekThurs(List<StatisticalSearchUserModel> lastWeekThurs) {
		this.lastWeekThurs = lastWeekThurs;
	}

	public List<StatisticalSearchUserModel> getLastWeekFri() {
		return lastWeekFri;
	}

	public void setLastWeekFri(List<StatisticalSearchUserModel> lastWeekFri) {
		this.lastWeekFri = lastWeekFri;
	}

	public List<StatisticalSearchUserModel> getLastWeekSat() {
		return lastWeekSat;
	}

	public void setLastWeekSat(List<StatisticalSearchUserModel> lastWeekSat) {
		this.lastWeekSat = lastWeekSat;
	}

	public List<StatisticalSearchUserModel> getLastWeekSun() {
		return lastWeekSun;
	}

	public void setLastWeekSun(List<StatisticalSearchUserModel> lastWeekSun) {
		this.lastWeekSun = lastWeekSun;
	}

	public List<StatisticalSearchUserModel> getThisWeekMon() {
		return thisWeekMon;
	}

	public void setThisWeekMon(List<StatisticalSearchUserModel> thisWeekMon) {
		this.thisWeekMon = thisWeekMon;
	}

	public List<StatisticalSearchUserModel> getThisWeekTues() {
		return thisWeekTues;
	}

	public void setThisWeekTues(List<StatisticalSearchUserModel> thisWeekTues) {
		this.thisWeekTues = thisWeekTues;
	}

	public List<StatisticalSearchUserModel> getThisWeekWed() {
		return thisWeekWed;
	}

	public void setThisWeekWed(List<StatisticalSearchUserModel> thisWeekWed) {
		this.thisWeekWed = thisWeekWed;
	}

	public List<StatisticalSearchUserModel> getThisWeekThurs() {
		return thisWeekThurs;
	}

	public void setThisWeekThurs(List<StatisticalSearchUserModel> thisWeekThurs) {
		this.thisWeekThurs = thisWeekThurs;
	}

	public List<StatisticalSearchUserModel> getThisWeekFri() {
		return thisWeekFri;
	}

	public void setThisWeekFri(List<StatisticalSearchUserModel> thisWeekFri) {
		this.thisWeekFri = thisWeekFri;
	}

	public List<StatisticalSearchUserModel> getThisWeekSat() {
		return thisWeekSat;
	}

	public void setThisWeekSat(List<StatisticalSearchUserModel> thisWeekSat) {
		this.thisWeekSat = thisWeekSat;
	}

	public List<StatisticalSearchUserModel> getThisWeekSun() {
		return thisWeekSun;
	}

	public void setThisWeekSun(List<StatisticalSearchUserModel> thisWeekSun) {
		this.thisWeekSun = thisWeekSun;
	}

	public int getWhatThisDay() {
		return whatThisDay;
	}

	public void setWhatThisDay(int whatThisDay) {
		this.whatThisDay = whatThisDay;
	}
	
}
