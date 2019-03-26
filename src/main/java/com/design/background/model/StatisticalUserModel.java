package com.design.background.model;

import java.util.List;

/**
 * 统计分析用户量数据保存返回给前端的实体类
 *  时间:2019/2/20
 * 最后修改时间:2019/2/20
 * 注意事项:无
* @author 周天
*
*/
public class StatisticalUserModel {
	
	private int weekProjectUser = 0; // 一周内新注册项目方用户的数量 ，用户数量所有默认值为0
	
	private int weekDesignUser = 0; // 一周内新注册设计师用户的数量
	
	private int weekExpertUser = 0; // 一周内新注册专家用户的数量
	
	private int monthProjectUser = 0; // 一月内新注册项目方用户的数量
	
	private int monthDesignUser = 0; // 一月内新注册设计师用户的数量
	
	private int monthExpertUser = 0; // 一月内新注册专家用户的数量
	
	private int allProjectUser = 0; // 全部注册项目方用户的数量
	
	private int allDesignUser = 0; // 全部注册设计师用户的数量
	
	private int allExpertUser = 0; // 全部注册专家用户的数量
	
	private int allUser = 0; // 平台所有类型用户的数量
	
	private int allMonthUser = 0; // 平台本月所有类型用户的数量


	private List<StatisticalSearchUserModel> lastStatisticalSearchUserModel;

	private List<StatisticalSearchUserModel> thisStatisticalSearchUserModel;
	
	private List<StatisticalSearchUserBalanceModel> topTenCity; // 所有用户数量前十的城市
	
	private List<StatisticalSearchUserCountModel> topTenBalanceUser; // 所有用户余额前十的用户
	
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

		public int getWeekProjectUser() {
			return weekProjectUser;
		}
	
		public void setWeekProjectUser(int weekProjectUser) {
			this.weekProjectUser = weekProjectUser;
		}
	
		public int getWeekDesignUser() {
			return weekDesignUser;
		}
	
		public void setWeekDesignUser(int weekDesignUser) {
			this.weekDesignUser = weekDesignUser;
		}
	
		public int getWeekExpertUser() {
			return weekExpertUser;
		}
	
		public void setWeekExpertUser(int weekExpertUser) {
			this.weekExpertUser = weekExpertUser;
		}
	
		public int getMonthProjectUser() {
			return monthProjectUser;
		}
	
		public void setMonthProjectUser(int monthProjectUser) {
			this.monthProjectUser = monthProjectUser;
		}
	
		public int getMonthDesignUser() {
			return monthDesignUser;
		}
	
		public void setMonthDesignUser(int monthDesignUser) {
			this.monthDesignUser = monthDesignUser;
		}
	
		public int getMonthExpertUser() {
			return monthExpertUser;
		}
	
		public void setMonthExpertUser(int monthExpertUser) {
			this.monthExpertUser = monthExpertUser;
		}
	
		public int getAllProjectUser() {
			return allProjectUser;
		}
	
		public void setAllProjectUser(int allProjectUser) {
			this.allProjectUser = allProjectUser;
		}
	
		public int getAllDesignUser() {
			return allDesignUser;
		}
	
		public void setAllDesignUser(int allDesignUser) {
			this.allDesignUser = allDesignUser;
		}
	
		public int getAllExpertUser() {
			return allExpertUser;
		}
	
		public void setAllExpertUser(int allExpertUser) {
			this.allExpertUser = allExpertUser;
		}
	
		public int getAllUser() {
			return allUser;
		}
	
		public void setAllUser(int allUser) {
			this.allUser = allUser;
		}
	
		public int getAllMonthUser() {
			return allMonthUser;
		}
	
		public void setAllMonthUser(int allMonthUser) {
			this.allMonthUser = allMonthUser;
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
			return "StatisticalUserModel [weekProjectUser=" + weekProjectUser + ", weekDesignUser=" + weekDesignUser
					+ ", weekExpertUser=" + weekExpertUser + ", monthProjectUser=" + monthProjectUser
					+ ", monthDesignUser=" + monthDesignUser + ", monthExpertUser=" + monthExpertUser
					+ ", allProjectUser=" + allProjectUser + ", allDesignUser=" + allDesignUser + ", allExpertUser="
					+ allExpertUser + ", allUser=" + allUser + ", allMonthUser=" + allMonthUser + ", topTenCity="
					+ topTenCity + ", topTenBalanceUser=" + topTenBalanceUser + ", lastWeekMon=" + lastWeekMon
					+ ", lastWeekTues=" + lastWeekTues + ", lastWeekWed=" + lastWeekWed + ", lastWeekThurs="
					+ lastWeekThurs + ", lastWeekFri=" + lastWeekFri + ", lastWeekSat=" + lastWeekSat + ", lastWeekSun="
					+ lastWeekSun + ", thisWeekMon=" + thisWeekMon + ", thisWeekTues=" + thisWeekTues + ", thisWeekWed="
					+ thisWeekWed + ", thisWeekThurs=" + thisWeekThurs + ", thisWeekFri=" + thisWeekFri
					+ ", thisWeekSat=" + thisWeekSat + ", thisWeekSun=" + thisWeekSun + ", whatThisDay=" + whatThisDay
					+ "]";
		}
		


	
}
