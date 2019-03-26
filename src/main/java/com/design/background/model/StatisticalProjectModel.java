package com.design.background.model;

import java.util.List;

/**
 * 统计分析项目量数据保存返回给前端的实体类
 *  时间:2019/2/20
 * 最后修改时间:2019/2/22
 * 注意事项:无
* @author 周天
*
*/
public class StatisticalProjectModel {
	
	private int weekMiddelProject = 0; // 一周内在建项目的数量 ，用户数量所有默认值为0
	
	private int weekNewProject = 0; // 一周内新建项目的数量
	
	private int weekEndProject = 0; // 一周内结项项目的数量
	
	private int monthMiddelProject = 0; // 一月内在建项目的数量
	
	private int monthNewProject = 0; // 一月内新建项目的数量

	private int monthEndProject = 0; // 一月内结项项目的数量
	
	private int allMiddelProject = 0; // 全部在建项目的数量
	
	private int allNewProject = 0; // 全部新建项目的数量
	
	private int allEndProject = 0; // 全部结项项目的数量
	
	private int allProject = 0; // 平台所有类型项目的数量
	
	private int allMonthProject = 0; // 平台本月所有类型项目的数量
	
	private List<StatisticalSearchUserBalanceModel> topTenCity; // 完成项目数量前十的城市
	
	private List<StatisticalSearchUserCountModel> topTenBalanceUser; // 完成项目前十的设计师
	
	// 上周的 StatisticalSearchUserModel 数据
		private List<StatisticalSearchUserModel> lastWeekMon;
		
		private List<StatisticalSearchUserModel> lastWeekTues;
		
		private List<StatisticalSearchUserModel> lastWeekWed;
		
		private List<StatisticalSearchUserModel> lastWeekThurs;
		
		private List<StatisticalSearchUserModel> lastWeekFri;
		
		private List<StatisticalSearchUserModel> lastWeekSat;

		private List<StatisticalSearchUserModel> lastWeekSun;

		private List<StatisticalSearchUserModel> lastStatisticalSearchUserModel;

		private List<StatisticalSearchUserModel> thisStatisticalSearchUserModel;

	// 本周的 StatisticalSearchUserModel 数据
		private List<StatisticalSearchUserModel> thisWeekMon;
		
		private List<StatisticalSearchUserModel> thisWeekTues;
		
		private List<StatisticalSearchUserModel> thisWeekWed;
		
		private List<StatisticalSearchUserModel> thisWeekThurs;
		
		private List<StatisticalSearchUserModel> thisWeekFri;
		
		private List<StatisticalSearchUserModel> thisWeekSat;
		
		private List<StatisticalSearchUserModel> thisWeekSun;
		
		private int whatThisDay = 0;  // 今天与星期一差几天

		public int getWeekMiddelProject() {
			return weekMiddelProject;
		}

		public void setWeekMiddelProject(int weekMiddelProject) {
			this.weekMiddelProject = weekMiddelProject;
		}

		public int getWeekNewProject() {
			return weekNewProject;
		}

		public void setWeekNewProject(int weekNewProject) {
			this.weekNewProject = weekNewProject;
		}

		public int getWeekEndProject() {
			return weekEndProject;
		}

		public void setWeekEndProject(int weekEndProject) {
			this.weekEndProject = weekEndProject;
		}

		public int getMonthMiddelProject() {
			return monthMiddelProject;
		}

		public void setMonthMiddelProject(int monthMiddelProject) {
			this.monthMiddelProject = monthMiddelProject;
		}

		public int getMonthNewProject() {
			return monthNewProject;
		}

		public void setMonthNewProject(int monthNewProject) {
			this.monthNewProject = monthNewProject;
		}

		public int getMonthEndProject() {
			return monthEndProject;
		}

		public void setMonthEndProject(int monthEndProject) {
			this.monthEndProject = monthEndProject;
		}

		public int getAllMiddelProject() {
			return allMiddelProject;
		}

		public void setAllMiddelProject(int allMiddelProject) {
			this.allMiddelProject = allMiddelProject;
		}

		public int getAllNewProject() {
			return allNewProject;
		}

		public void setAllNewProject(int allNewProject) {
			this.allNewProject = allNewProject;
		}

		public int getAllEndProject() {
			return allEndProject;
		}

		public void setAllEndProject(int allEndProject) {
			this.allEndProject = allEndProject;
		}

		public int getAllProject() {
			return allProject;
		}

		public void setAllProject(int allProject) {
			this.allProject = allProject;
		}

		public int getAllMonthProject() {
			return allMonthProject;
		}

		public void setAllMonthProject(int allMonthProject) {
			this.allMonthProject = allMonthProject;
		}

		public List<StatisticalSearchUserBalanceModel> getTopTenCity() {
			return topTenCity;
		}

		public void setTopTenCity(List<StatisticalSearchUserBalanceModel> topTenCity) {
			this.topTenCity = topTenCity;
		}

		public List<StatisticalSearchUserCountModel> getTopTenBalanceUser() {
			return topTenBalanceUser;
		}

		public void setTopTenBalanceUser(List<StatisticalSearchUserCountModel> topTenBalanceUser) {
			this.topTenBalanceUser = topTenBalanceUser;
		}

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
		public List<StatisticalSearchUserModel> getLastStatisticalSearchUserModel() {
			return lastStatisticalSearchUserModel;
		}

		public void setLastStatisticalSearchUserModel(List<StatisticalSearchUserModel> lastStatisticalSearchUserModel) {
			this.lastStatisticalSearchUserModel = lastStatisticalSearchUserModel;
		}

		public List<StatisticalSearchUserModel> getThisStatisticalSearchUserModel() {
			return thisStatisticalSearchUserModel;
		}

		public void setThisStatisticalSearchUserModel(List<StatisticalSearchUserModel> thisStatisticalSearchUserModel) {
			this.thisStatisticalSearchUserModel = thisStatisticalSearchUserModel;
		}
		@Override
		public String toString() {
			return "StatisticalProjectModel [weekMiddelProject=" + weekMiddelProject + ", weekNewProject="
					+ weekNewProject + ", weekEndProject=" + weekEndProject + ", monthMiddelProject="
					+ monthMiddelProject + ", monthNewProject=" + monthNewProject + ", monthEndProject="
					+ monthEndProject + ", allMiddelProject=" + allMiddelProject + ", allNewProject=" + allNewProject
					+ ", allEndProject=" + allEndProject + ", allProject=" + allProject + ", allMonthProject="
					+ allMonthProject + ", topTenCity=" + topTenCity + ", topTenBalanceUser=" + topTenBalanceUser
					+ ", lastWeekMon=" + lastWeekMon + ", lastWeekTues=" + lastWeekTues + ", lastWeekWed=" + lastWeekWed
					+ ", lastWeekThurs=" + lastWeekThurs + ", lastWeekFri=" + lastWeekFri + ", lastWeekSat="
					+ lastWeekSat + ", lastWeekSun=" + lastWeekSun + ", thisWeekMon=" + thisWeekMon + ", thisWeekTues="
					+ thisWeekTues + ", thisWeekWed=" + thisWeekWed + ", thisWeekThurs=" + thisWeekThurs
					+ ", thisWeekFri=" + thisWeekFri + ", thisWeekSat=" + thisWeekSat + ", thisWeekSun=" + thisWeekSun
					+ ", whatThisDay=" + whatThisDay + "]";
		}

		

		
}
