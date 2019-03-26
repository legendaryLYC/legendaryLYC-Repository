package com.design.background.entity;

public class BasicInformation {
    private Long id;

    private String webUrl;

    private String title;

    private String titleImg;

    private String phone;

    private String icpInformation;

    private String wechatImg;

    private String emailAddress;

    private Integer isSelected;

    private String footImg;

    public String getFootImg() {
        return footImg;
    }

    public void setFootImg(String footImg) {
        this.footImg = footImg;
    }

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

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg == null ? null : titleImg.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIcpInformation() {
        return icpInformation;
    }

    public void setIcpInformation(String icpInformation) {
        this.icpInformation = icpInformation == null ? null : icpInformation.trim();
    }

    public String getWechatImg() {
        return wechatImg;
    }

    public void setWechatImg(String wechatImg) {
        this.wechatImg = wechatImg == null ? null : wechatImg.trim();
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress == null ? null : emailAddress.trim();
    }
}