package com.design.background.entity;

import java.util.Date;

public class DicDefaultRate {
    private Long id;

    private Integer waterRate;

    private Integer warmRate;

    private Integer structureRate;

    private Integer buildingRate;

    private Integer electricityRate;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(Integer waterRate) {
        this.waterRate = waterRate;
    }

    public Integer getWarmRate() {
        return warmRate;
    }

    public void setWarmRate(Integer warmRate) {
        this.warmRate = warmRate;
    }

    public Integer getStructureRate() {
        return structureRate;
    }

    public void setStructureRate(Integer structureRate) {
        this.structureRate = structureRate;
    }

    public Integer getBuildingRate() {
        return buildingRate;
    }

    public void setBuildingRate(Integer buildingRate) {
        this.buildingRate = buildingRate;
    }

    public Integer getElectricityRate() {
        return electricityRate;
    }

    public void setElectricityRate(Integer electricityRate) {
        this.electricityRate = electricityRate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}