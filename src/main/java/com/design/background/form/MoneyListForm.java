package com.design.background.form;

import com.design.background.entity.MoneyDistribution;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: MoneyListForm
 * @Description: TODO 创建模型类用来接收form表单传来的相同类型的数据
 * @Author: HRX
 * @Date: 2019/2/22 15:14
 **/
public class MoneyListForm {
    private BigDecimal waterMoney;

    private BigDecimal warmMoney;

    private BigDecimal structureMoney;

    private BigDecimal buildingMoney;

    private BigDecimal electricityMoney;

    private BigDecimal waterMoney1;

    private BigDecimal warmMoney1;

    private BigDecimal structureMoney1;

    private BigDecimal buildingMoney1;

    private BigDecimal electricityMoney1;

    MoneyDistribution moneyStage1 = new MoneyDistribution();

    MoneyDistribution moneyStage2 = new MoneyDistribution();

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

    public BigDecimal getWarmMoney1() {
        return warmMoney1;
    }

    public void setWarmMoney1(BigDecimal warmMoney1) {
        this.warmMoney1 = warmMoney1;
    }

    public MoneyDistribution getMoneyStage1() {
        return moneyStage1;
    }

    public MoneyDistribution getMoneyStage2() {
        return moneyStage2;
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

    public BigDecimal getWaterMoney1() {
        return waterMoney1;
    }

    public void setWaterMoney1(BigDecimal waterMoney1) {
        this.waterMoney1 = waterMoney1;
    }

    public BigDecimal getStructureMoney1() {
        return structureMoney1;
    }

    public void setStructureMoney1(BigDecimal structureMoney1) {
        this.structureMoney1 = structureMoney1;
    }

    public BigDecimal getBuildingMoney1() {
        return buildingMoney1;
    }

    public void setBuildingMoney1(BigDecimal buildingMoney1) {
        this.buildingMoney1 = buildingMoney1;
    }

    public BigDecimal getElectricityMoney1() {
        return electricityMoney1;
    }

    public void setElectricityMoney1(BigDecimal electricityMoney1) {
        this.electricityMoney1 = electricityMoney1;
    }

    public MoneyDistribution getMoneyStage1(Long productId) {
        moneyStage1.setWaterMoney(waterMoney);
        moneyStage1.setWarmMoney(warmMoney);
        moneyStage1.setStructureMoney(structureMoney);
        moneyStage1.setElectricityMoney(electricityMoney);
        moneyStage1.setBuildingMoney(buildingMoney);
        moneyStage1.setCreateTime(new Date());
        moneyStage1.setStageCode(1000);
        moneyStage1.setProjectId(productId);
        return moneyStage1;
    }

    public void setMoneyStage1(MoneyDistribution moneyStage1) {
        this.moneyStage1 = moneyStage1;
    }

    public MoneyDistribution getMoneyStage2(Long productId) {
        moneyStage2.setWaterMoney(waterMoney1);
        moneyStage2.setWarmMoney(warmMoney1);
        moneyStage2.setStructureMoney(structureMoney1);
        moneyStage2.setElectricityMoney(electricityMoney1);
        moneyStage2.setBuildingMoney(buildingMoney1);
        moneyStage2.setCreateTime(new Date());
        moneyStage2.setStageCode(1001);
        moneyStage2.setProjectId(productId);
        return moneyStage2;
    }

    public void setMoneyStage2(MoneyDistribution moneyStage2) {
        this.moneyStage2 = moneyStage2;
    }
}
