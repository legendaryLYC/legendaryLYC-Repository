package com.design.background.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MoneyDistribution {
    private Long id;

    private BigDecimal waterMoney;

    private BigDecimal warmMoney;

    private BigDecimal structureMoney;

    private BigDecimal buildingMoney;

    private BigDecimal electricityMoney;

    private Date createTime;

    private Long projectId;

    private Integer stageCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getWaterMoney() {
        return waterMoney;
    }

    public void setWaterMoney(BigDecimal waterMoney) {
        this.waterMoney = waterMoney;
    }

    public BigDecimal getWarmMoney() {
        return warmMoney;
    }

    public void setWarmMoney(BigDecimal warmMoney) {
        this.warmMoney = warmMoney;
    }

    public BigDecimal getStructureMoney() {
        return structureMoney;
    }

    public void setStructureMoney(BigDecimal structureMoney) {
        this.structureMoney = structureMoney;
    }

    public BigDecimal getBuildingMoney() {
        return buildingMoney;
    }

    public void setBuildingMoney(BigDecimal buildingMoney) {
        this.buildingMoney = buildingMoney;
    }

    public BigDecimal getElectricityMoney() {
        return electricityMoney;
    }

    public void setElectricityMoney(BigDecimal electricityMoney) {
        this.electricityMoney = electricityMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}