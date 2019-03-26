package com.design.background.form;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionForm {
	 	private Long id;

	    private String projectId;

	    private Integer userId;

	    private String content;

	    private Date creatTime;

	    private BigDecimal money;
	    
	    private String name;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getProjectId() {
	        return projectId;
	    }

	    public void setProjectId(String projectId) {
	        this.projectId = projectId == null ? null : projectId.trim();
	    }

	    public Integer getUserId() {
	        return userId;
	    }

	    public void setUserId(Integer userId) {
	        this.userId = userId;
	    }

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content == null ? null : content.trim();
	    }

	    public Date getCreatTime() {
	        return creatTime;
	    }

	    public void setCreatTime(Date creatTime) {
	        this.creatTime = creatTime;
	    }

	    public BigDecimal getMoney() {
	        return money;
	    }

	    public void setMoney(BigDecimal money) {
	        this.money = money;
	    }
	    
	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name == null ? null : name.trim();
	    }
}
