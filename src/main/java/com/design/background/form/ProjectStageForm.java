package com.design.background.form;

import java.util.Date;

/**
 * @Author： 李紫林
 * @Date： 2019/2/21
 * @Description：
 */
public class ProjectStageForm {
    private Long id;

    private String name;

    private Integer stageCode;

    private String stage;

    private Integer reviewCode;

    private String review;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStageCode() {
        return stageCode;
    }

    public void setStageCode(Integer stageCode) {
        this.stageCode = stageCode;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Integer getReviewCode() {
        return reviewCode;
    }

    public void setReviewCode(Integer reviewCode) {
        this.reviewCode = reviewCode;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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
}
