package com.design.background.form;

import java.math.BigDecimal;
import java.util.Date;

import com.design.background.util.StringToDate;

public class ProjectorForm {
	// 项目方相关字典表字段

	private String cityId;
	private String provinceId;
	private String areaname;
	private String cityname;
	private String provincename;
	//下面是项目方信息字段
	
	 private Long id;

	    private String tel;

	    private String email;

	    private String commonAddress;

	    private String nickname;

	    private String photo;

	    private Integer area; //存的是area id

		private Date createTime;

	    private String password;

	    private Date updateTime;

	    private String username;

	    private Integer sexType;

	    private BigDecimal qualifyScore;

	    private Integer roleCode;

	    private String introduction;

		public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getTel() {
	        return tel;
	    }

	    public void setTel(String tel) {
	        this.tel = tel == null ? null : tel.trim();
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email == null ? null : email.trim();
	    }

	    public String getCommonAddress() {
	        return commonAddress;
	    }

	    public void setCommonAddress(String commonAddress) {
	        this.commonAddress = commonAddress == null ? null : commonAddress.trim();
	    }

	    public String getNickname() {
	        return nickname;
	    }

	    public void setNickname(String nickname) {
	        this.nickname = nickname == null ? null : nickname.trim();
	    }

	    public String getPhoto() {
	        return photo;
	    }

	    public void setPhoto(String photo) {
	        this.photo = photo == null ? null : photo.trim();
	    }

	    public Integer getArea() {
	        return area;
	    }

	    public void setArea(Integer area) {
	        this.area = area;
	    }

	    public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }
	    public void setCreateTime(String createTime) {
	    	Date time=StringToDate.toSqlDate(createTime);
	        this.createTime = time;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password == null ? null : password.trim();
	    }

	    public Date getUpdateTime() {
	        return updateTime;
	    }

	    public void setUpdateTime(Date updateTime) {
	        this.updateTime = updateTime;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username == null ? null : username.trim();
	    }

	    public Integer getSexType() {
	        return sexType;
	    }

	    public void setSexType(Integer sexType) {
	        this.sexType = sexType;
	    }

	    public BigDecimal getQualifyScore() {
	        return qualifyScore;
	    }

	    public void setQualifyScore(BigDecimal qualifyScore) {
	        this.qualifyScore = qualifyScore;
	    }

	    public Integer getRoleCode() {
	        return roleCode;
	    }

	    public void setRoleCode(Integer roleCode) {
	        this.roleCode = roleCode;
	    }

	    public String getIntroduction() {
	        return introduction;
	    }

	    public void setIntroduction(String introduction) {
	        this.introduction = introduction == null ? null : introduction.trim();
	    }
	
	
	
	

	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}




	
	
	
}
