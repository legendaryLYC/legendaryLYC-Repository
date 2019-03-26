package com.design.background.model;

import java.util.List;

/**
 * 统计分析投标量数据保存返回给前端的实体类
 *  时间:2019/2/21
 * 最后修改时间:2019/2/21
 * 注意事项:无
* @author 周天
*
*/
public class StatisticalSearchTenderModel {
	
	List<Integer> twelveMonth; // 投标量十二个月的数据,截止到本月前
	
	List<Integer> lastWeek; // 投标量上周七天的数据
	
	List<Integer> thisWeek; // 投标量本周内的数据
	
	List<StatisticalSearchUserBalanceModel> projectName; // 被投标量前十的项目名字
	
	List<StatisticalSearchUserBalanceModel> designName; // 投标量前世的设计师名字

	public List<Integer> getTwelveMonth() {
		return twelveMonth;
	}

	public void setTwelveMonth(List<Integer> twelveMonth) {
		this.twelveMonth = twelveMonth;
	}

	public List<Integer> getLastWeek() {
		return lastWeek;
	}

	public void setLastWeek(List<Integer> lastWeek) {
		this.lastWeek = lastWeek;
	}

	public List<Integer> getThisWeek() {
		return thisWeek;
	}

	public void setThisWeek(List<Integer> thisWeek) {
		this.thisWeek = thisWeek;
	}

	public List<StatisticalSearchUserBalanceModel> getProjectName() {
		return projectName;
	}

	public void setProjectName(List<StatisticalSearchUserBalanceModel> projectName) {
		this.projectName = projectName;
	}

	public List<StatisticalSearchUserBalanceModel> getDesignName() {
		return designName;
	}

	public void setDesignName(List<StatisticalSearchUserBalanceModel> designName) {
		this.designName = designName;
	}
	

}
