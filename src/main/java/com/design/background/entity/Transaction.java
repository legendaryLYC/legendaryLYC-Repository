package com.design.background.entity;

import java.math.BigDecimal;
import java.util.Date;

/**

 * @Author     :高红亮

 * @Date       :2019/2/23

 * @Description:交易记录列表类

*/

public class Transaction {
    private Long id;

    private String projectId;

    private Long userId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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