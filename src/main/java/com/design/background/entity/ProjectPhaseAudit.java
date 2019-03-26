package com.design.background.entity;

import java.util.Date;

public class ProjectPhaseAudit {
    private Long id;

    private Long projectId;

    private Integer stageCode;

    private Integer reviewCode;

    private Date createTime;

    private Date updateTime;

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

    public Integer getStageCode() {
        return stageCode;
    }

    public void setStageCode(Integer stageCode) {
        this.stageCode = stageCode;
    }

    public Integer getReviewCode() {
        return reviewCode;
    }

    public void setReviewCode(Integer reviewCode) {
        this.reviewCode = reviewCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public ProjectPhaseAudit(Long projectId, Integer stageCode, Integer reviewCode, Date createTime, Date updateTime) {
        this.projectId = projectId;
        this.stageCode = stageCode;
        this.reviewCode = reviewCode;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProjectPhaseAudit(Long id, Long projectId, Integer stageCode, Integer reviewCode, Date createTime, Date updateTime) {
        this.id = id;
        this.projectId = projectId;
        this.stageCode = stageCode;
        this.reviewCode = reviewCode;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProjectPhaseAudit() {
    }
}