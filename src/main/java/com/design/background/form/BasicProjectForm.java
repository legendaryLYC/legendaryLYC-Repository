package com.design.background.form;

import java.math.BigDecimal;

/**
 * 用于项目大厅列表显示项目缩略信息
 * @author 孟晓冬
 */
public class BasicProjectForm {
	
	private Long id;
	
	//private String status;
	
	private Integer statusCode;
	
	private String name;
	
	private BigDecimal constructionPeriod;	//工期
	
	private BigDecimal constructionBudget;	//工程总费用
	
	private String coverImage;
	
	private String componentCode;	//需要的设计师类型
	
	private String[] unSelectedDesigners;	//该项目待选择的设计师类型数组, 为了性能对应项目详情需要的设计师

//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getConstructionPeriod() {
		return constructionPeriod;
	}

	public void setConstructionPeriod(BigDecimal constructionPeriod) {
		this.constructionPeriod = constructionPeriod;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public String[] getUnSelectedDesigners() {
//		return unSelectedDesigners;
//	}
//
//	public void setUnSelectedDesigners(String[] unSelectedDesigners) {
//		this.unSelectedDesigners = unSelectedDesigners;
//	}

	public BigDecimal getConstructionBudget() {
		return constructionBudget;
	}

	public void setConstructionBudget(BigDecimal constructionBudget) {
		this.constructionBudget = constructionBudget;
	}

	public String getComponentCode() {
		return componentCode;
	}

	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String[] getUnSelectedDesigners() {
		return unSelectedDesigners;
	}

	public void setUnSelectedDesigners(String[] unSelectedDesigners) {
		this.unSelectedDesigners = unSelectedDesigners;
	}
}
