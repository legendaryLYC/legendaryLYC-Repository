package com.design.background.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.design.background.util.StringToDate;
public class LeadingUser {
    private Long id;

    private String tel;

    private String email;

    private String commonAddress;

    private String nickname;

    private String photo;

    private String aptitude;//资质证书存储路径

    private Integer area;
    
    private Integer cityId;
    
    private Integer provinceId;

	private Date createTime;

    private String password;

    private Date updateTime;

    private String username;

    private Integer sexType;

    private BigDecimal qualifyScore;

    private Integer roleCode;

    private String introduction;
   
    public LeadingUser() {
		super();
	}

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
    public String getAptitude() {
        return aptitude;
    }

    public void setAptitude(String aptitude) {
        this.aptitude = aptitude;
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
    public LeadingUser(String tel, String email, String commonAddress, String nickname, String photo,
			Integer area, String password, String username, Integer sexType,
			BigDecimal qualifyScore, Integer roleCode, String introduction,Date createTime,String aptitude) {
		super();
		this.tel = tel;
		this.email = email;
		this.commonAddress = commonAddress;
		this.nickname = nickname;
		this.photo = photo;
		this.area = area;
		this.password = password;
		this.username = username;
		this.sexType = sexType;
		this.qualifyScore = qualifyScore;
		this.roleCode = roleCode;
		this.introduction = introduction;
		this.createTime = createTime;
        this.aptitude = aptitude;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

    @Override
    public String toString() {
        return "LeadingUser{" +
                "id=" + id +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", commonAddress='" + commonAddress + '\'' +
                ", nickname='" + nickname + '\'' +
                ", photo='" + photo + '\'' +
                ", aptitude='" + aptitude + '\'' +
                ", area=" + area +
                ", cityId=" + cityId +
                ", provinceId=" + provinceId +
                ", createTime=" + createTime +
                ", password='" + password + '\'' +
                ", updateTime=" + updateTime +
                ", username='" + username + '\'' +
                ", sexType=" + sexType +
                ", qualifyScore=" + qualifyScore +
                ", roleCode=" + roleCode +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}