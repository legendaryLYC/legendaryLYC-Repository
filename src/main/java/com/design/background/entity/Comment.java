package com.design.background.entity;

import java.util.Date;

public class Comment {
    private Long id;

    private Long projectId;

    private String projectName;

    private String content;

    private Integer typeCode;

    private Integer processCode;

    private Long userId;

    private String userName; // 冗余字段 不与数据库的字段相匹配

    private Date submitTime;

    private Date processTime;

    private Long processorId;

    private String auditName; // 冗余字段 不与数据库的字段相匹配

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getProcessCode() {
        return processCode;
    }

    public void setProcessCode(Integer processCode) {
        this.processCode = processCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public Long getProcessorId() {
        return processorId;
    }

    public void setProcessorId(Long processorId) {
        this.processorId = processorId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }
}