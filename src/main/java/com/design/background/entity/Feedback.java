package com.design.background.entity;

import java.util.Date;


/**
 * Feedback class
 *
 * @author 李紫林
 * @date 2019/2/26
 */
public class Feedback {
    private Long id;

    private Long feedbackUserId;

    private String title;

    private Date submitTime;

    private Date processTime;

    private Long processorId;

    private String userName;

    private String tel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeedbackUserId() {
        return feedbackUserId;
    }

    public void setFeedbackUserId(Long feedbackUserId) {
        this.feedbackUserId = feedbackUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Feedback(Long id, Long feedbackUserId, String title, Date submitTime, Date processTime, Long processorId) {
        this.id = id;
        this.feedbackUserId = feedbackUserId;
        this.title = title;
        this.submitTime = submitTime;
        this.processTime = processTime;
        this.processorId = processorId;
    }

    public Feedback() {
    }
}