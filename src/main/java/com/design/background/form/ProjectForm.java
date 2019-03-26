package com.design.background.form;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.design.background.entity.ProjectFiles;

/**
 * 项目详情字段和相关字典表字段
 * @author 孟晓冬
 *
 */
public class ProjectForm {
	//相关字典表字段
	private String projectType;
	private String area;
	private String city;
	private String province;
	private String model;
	private String stage;
	private String dispatch;
	private String status;
	private String userName;
	private List<ProjectFiles> projectFiles;
	private String reviewStatus; // 阶段审核状态
	
	//以下是项目详情的字段
	private Long id;

    private String name;

    private Integer typeCode;

    private Integer areaId;

    private Integer modelCode;

    private String projectFunction;

    private Integer stageCode;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date deadline;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startupDate;

    private BigDecimal constructionBudget;

    private BigDecimal designBudget;

    private Integer dispatchCode;

    private String allowView;

    private Integer processCode;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;

    private Long pageView;

    private String introduce;

    private String remarks;

    private String requirements;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date processTime;

    private Long processorId;

    private Long userId;

    private Integer projectProgress;

    private Integer completeAudting;

    private Integer isTop;

    private Integer isDelete;
    
    private Integer constructionPeriod;
    
    private String coverImage;
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date registrationDeadline;
    
    private String componentCode;
    
    private BigDecimal planBudget;//施工图阶段费用
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date create_time;
    
    private Integer cityId;
    
	private Integer provinceId;

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
        this.name = name == null ? null : name.trim();
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getModelCode() {
        return modelCode;
    }

    public void setModelCode(Integer modelCode) {
        this.modelCode = modelCode;
    }

    public String getProjectFunction() {
        return projectFunction;
    }

    public void setProjectFunction(String projectFunction) {
        this.projectFunction = projectFunction == null ? null : projectFunction.trim();
    }

    public Integer getStageCode() {
        return stageCode;
    }

    public void setStageCode(Integer stageCode) {
        this.stageCode = stageCode;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getStartupDate() {
        return startupDate;
    }

    public void setStartupDate(Date startupDate) {
        this.startupDate = startupDate;
    }

    public BigDecimal getConstructionBudget() {
        return constructionBudget;
    }

    public void setConstructionBudget(BigDecimal constructionBudget) {
        this.constructionBudget = constructionBudget;
    }

    public BigDecimal getDesignBudget() {
        return designBudget;
    }

    public void setDesignBudget(BigDecimal designBudget) {
        this.designBudget = designBudget;
    }

    public Integer getDispatchCode() {
        return dispatchCode;
    }

    public void setDispatchCode(Integer dispatchCode) {
        this.dispatchCode = dispatchCode;
    }

    public String getAllowView() {
        return allowView;
    }

    public void setAllowView(String allowView) {
        this.allowView = allowView == null ? null : allowView.trim();
    }

    public Integer getProcessCode() {
        return processCode;
    }

    public void setProcessCode(Integer processCode) {
        this.processCode = processCode;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Long getPageView() {
        return pageView;
    }

    public void setPageView(Long pageView) {
        this.pageView = pageView;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements == null ? null : requirements.trim();
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(Integer projectProgress) {
        this.projectProgress = projectProgress;
    }

    public Integer getCompleteAudting() {
        return completeAudting;
    }

    public void setCompleteAudting(Integer completeAudting) {
        this.completeAudting = completeAudting;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDispatch() {
		return dispatch;
	}

	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getConstructionPeriod() {
		return constructionPeriod;
	}

	public void setConstructionPeriod(Integer constructionPeriod) {
		this.constructionPeriod = constructionPeriod;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public Date getRegistrationDeadline() {
		return registrationDeadline;
	}

	public void setRegistrationDeadline(Date registrationDeadline) {
		this.registrationDeadline = registrationDeadline;
	}

	public String getComponentCode() {
		return componentCode;
	}

	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}

	public List<ProjectFiles> getProjectFiles() {
		return projectFiles;
	}

	public void setProjectFiles(List<ProjectFiles> projectFiles) {
		this.projectFiles = projectFiles;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public BigDecimal getPlanBudget() {
		return planBudget;
	}

	public void setPlanBudget(BigDecimal planBudget) {
		this.planBudget = planBudget;
	}

	public Date getCreateTime() {
		return create_time;
	}

	public void setCreateTime(Date creatTime) {
		this.create_time = creatTime;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
}
