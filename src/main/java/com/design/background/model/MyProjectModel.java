package com.design.background.model;

/**
 * 
* @ClassName: MyProjectModel  
* @Description: TODO 个人中心我的项目模型类  
* @author HRX  
* @date 2019年2月16日  
*
 */
public class MyProjectModel {
	
	private Long id; //项目id
	
	private String name; //项目名称
	
	private String stage;	//项目阶段
	
	private String status; //项目状态

	private String type; //建筑类型
	
	private String deadline; //报名截止日期
	
	private Integer isSelected; //是否被项目方选中

	private String stageReview; // 项目验收结果
	
	private Integer isPhaseAudit = 0; //是否被项目方选中
	
	private boolean showBeginBuild = false; //设计师是否已经集齐,开始建设按钮是否展示

	private Integer typeCode; //项目类型编码
	
	private Integer statusCode; //项目状态编码
	
	private Integer stageCode; // 项目阶段编码
	
	private Integer stageReviewCode; //阶段审核状态码

	private  String tel;
	
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

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public Integer getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Integer isSelected) {
		this.isSelected = isSelected;
	}

	public String getStageReview() {
		return stageReview;
	}

	public void setStageReview(String stageReview) {
		this.stageReview = stageReview;
	}

	public Integer getIsPhaseAudit() {
		return isPhaseAudit;
	}

	public void setIsPhaseAudit(Integer isPhaseAudit) {
		this.isPhaseAudit = isPhaseAudit;
	}

	public boolean isShowBeginBuild() {
		return showBeginBuild;
	}

	public void setShowBeginBuild(boolean showBeginBuild) {
		this.showBeginBuild = showBeginBuild;
	}

	public Integer getStausCode() {
		return statusCode;
	}

	public void setStausCode(Integer stausCode) {
		this.statusCode = stausCode;
	}

	public Integer getStageReviewCode() {
		return stageReviewCode;
	}

	public void setStageReviewCode(Integer stageReviewCode) {
		this.stageReviewCode = stageReviewCode;
	}

	public Integer getStageCode() {
		return stageCode;
	}

	public void setStageCode(Integer stageCode) {
		this.stageCode = stageCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(Integer typeCode) {
		this.typeCode = typeCode;
	}
}
