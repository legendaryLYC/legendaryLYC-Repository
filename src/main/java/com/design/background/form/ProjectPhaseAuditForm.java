package com.design.background.form;

import java.util.Date;
import java.util.List;

import com.design.background.entity.PhaseAuditFiles;

/**
 * 阶段审核form类
 * @author 孟晓冬
 */
public class ProjectPhaseAuditForm {
	
	private Long id;

    private Long projectId;

    private Integer stageCode;

    private Integer reviewCode;

    private Date createTime; // 申请时间

    private Date updateTime;


    private String stage; // 阶段描述
    
    private String reviewStatus;  // 当前审核状态
    
    private String projectName; // 项目名称

    private List<PhaseAuditFiles> files; // 设计师上传的阶段文件
    
    private String designerName;
    //设计师类型,当前未获取
    private String designerType;
    
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

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<PhaseAuditFiles> getFiles() {
		return files;
	}

	public void setFiles(List<PhaseAuditFiles> files) {
		this.files = files;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}

	public String getDesignerType() {
		return designerType;
	}

	public void setDesignerType(String designerType) {
		this.designerType = designerType;
	}

}
