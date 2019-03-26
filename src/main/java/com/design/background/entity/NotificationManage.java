package com.design.background.entity;

import java.sql.Timestamp;
import java.util.Date;

public class NotificationManage {
    private Long id;

    private String acceptUser;

    private String notifyType;

    private String sender;

    private String title;

    private Timestamp creatTime;

    private Integer isall;

    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcceptUser() {
        return acceptUser;
    }

    public void setAcceptUser(String acceptUser) {
        this.acceptUser = acceptUser;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType == null ? null : notifyType.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getIsall() {
        return isall;
    }

    public void setIsall(Integer isall) {
        this.isall = isall;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

	@Override
	public String toString() {
		return "NotificationManage [id=" + id + ", acceptUser=" + acceptUser + ", notifyType=" + notifyType
				+ ", sender=" + sender + ", title=" + title + ", creatTime=" + creatTime + ", isall=" + isall
				+ ", message=" + message + "]";
	}
    
}