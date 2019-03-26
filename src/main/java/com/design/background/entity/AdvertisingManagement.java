package com.design.background.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AdvertisingManagement implements Comparable<AdvertisingManagement>{
    private Long id;

    private String pictureUrl;

    private String title;

    private String content;

    private Integer locationCode;

    private String advertUrl;

    private Date term;

    private BigDecimal advertFee;

    private String remark;

    private Date creatTime;

    private String username;

    private Integer isSelected;

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(Integer locationCode) {
        this.locationCode = locationCode;
    }

    public String getAdvertUrl() {
        return advertUrl;
    }

    public void setAdvertUrl(String advertUrl) {
        this.advertUrl = advertUrl == null ? null : advertUrl.trim();
    }

    public Date getTerm() {
        return term;
    }

    public void setTerm(Date term) {
        this.term = term;
    }

    public BigDecimal getAdvertFee() {
        return advertFee;
    }

    public void setAdvertFee(BigDecimal advertFee) {
        this.advertFee = advertFee;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
    
	@Override
	public String toString() {
		return "AdvertisingManagement [id=" + id + ", pictureUrl=" + pictureUrl + ", title=" + title + ", content="
				+ content + ", locationCode=" + locationCode + ", advertUrl=" + advertUrl + ", term=" + term
				+ ", advertFee=" + advertFee + ", remark=" + remark + ", creatTime=" + creatTime + ", username="
				+ username + "]";
	}

    @Override
    public int compareTo(AdvertisingManagement o) {
        //按位置号升序排列
        return this.locationCode - o.getLocationCode();
    }
}