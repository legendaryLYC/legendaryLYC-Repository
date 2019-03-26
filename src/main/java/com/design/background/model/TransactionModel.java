package com.design.background.model;

import java.math.BigDecimal;
import java.util.Date;

/**

 * @ Author     :高红亮

 * @ Date       :2019/2/16

 * @ Description:对应后台交易记录页面所需数据的model类

*/

public class TransactionModel {
    private Long id;

    private String projectId;

    private Integer userId;

    private String content;

    private String creatTime;

    private BigDecimal money;

    private String userName;

    private String projectName;

    public TransactionModel(Long id, String projectId, Integer userId, String content, String createTime, BigDecimal money, String userName, String projectName) {
        this.id = id;
        this.projectId = projectId;
        this.content = content;
        this.money = money;
        this.creatTime = createTime;
        this.userId = userId;
        this.userName = userName;
        this.projectName = projectName;
    }

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

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
