package com.design.background.entity;

import java.util.Date;

public class DesignerRelationship {
    private Long id;

    private Long projectId;

    private Long userId;

    private Integer typeCode;

    private Integer isSelected;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
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

    public DesignerRelationship(Long projectId, Long userId, Integer typeCode, Integer isSelected) {
        this.projectId = projectId;
        this.userId = userId;
        this.typeCode = typeCode;
        this.isSelected = isSelected;
    }

    public DesignerRelationship() {
    }

    public DesignerRelationship(Long projectId, Long userId, Integer typeCode, Integer isSelected, Date createTime) {
        this.projectId = projectId;
        this.userId = userId;
        this.typeCode = typeCode;
        this.isSelected = isSelected;
        this.createTime = createTime;
    }
}