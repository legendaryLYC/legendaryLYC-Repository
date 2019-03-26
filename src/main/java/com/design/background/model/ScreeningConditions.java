package com.design.background.model;

import java.math.BigDecimal;

/**
 * 该类用来存储项目大厅的筛选条件
 * @author 孟晓冬
 */
public class ScreeningConditions {
	//工程费用起始值,单位万
	private BigDecimal constructionBudgetFrom;
	//工程费用终止值,单位万
	private BigDecimal constructionBudgetTo;
	//工期起始值,单位月
	private Integer constructionPeriodFrom;
	//工期结束值,单位月
	private Integer constructionPeriodTo;
	//组建分类待处理!
	private String component;
	//城市地区
	private Integer cityId;
	//城市描述
	private String cityDescription;
	//筛选要显示的项目状态数组
	private int[] status;
	
	public BigDecimal getConstructionBudgetFrom() {
		return constructionBudgetFrom;
	}
	public void setConstructionBudgetFrom(BigDecimal constructionBudgetFrom) {
		this.constructionBudgetFrom = constructionBudgetFrom;
	}
	public BigDecimal getConstructionBudgetTo() {
		return constructionBudgetTo;
	}
	public void setConstructionBudgetTo(BigDecimal constructionBudgetTo) {
		this.constructionBudgetTo = constructionBudgetTo;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public int[] getStatus() {
		return status;
	}
	public void setStatus(int[] status) {
		this.status = status;
	}
	public Integer getConstructionPeriodFrom() {
		return constructionPeriodFrom;
	}
	public void setConstructionPeriodFrom(Integer constructionPeriodFrom) {
		this.constructionPeriodFrom = constructionPeriodFrom;
	}
	public Integer getConstructionPeriodTo() {
		return constructionPeriodTo;
	}
	public void setConstructionPeriodTo(Integer constructionPeriodTo) {
		this.constructionPeriodTo = constructionPeriodTo;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = "".equals(component) ? null : component;
	}
	public String getCityDescription() {
		return cityDescription;
	}
	public void setCityDescription(String cityDescription) {
		this.cityDescription = cityDescription;
	}
}
