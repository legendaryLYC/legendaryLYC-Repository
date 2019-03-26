package com.design.background.form;

import java.math.BigDecimal;
import java.util.Date;

import com.design.background.util.StringToDate;

/**
 * 设计师详情字段和相关字典表字段
 * @author 宋博通
 *
 */
public class DesignerForm {
    private Long id;

    private String tel;

    private String email;

    private String commonAddress;

    private String nickname;

    private String photo;

    private String aptitude;

    private Integer area;

	private Date createTime;

    private String password;

    private Date updateTime;

    private String username;

    private Integer sexType;

    private BigDecimal qualifyScore;

    private Integer roleCode;

    private String introduction;

    private Integer code;
   
    private String description;
    
    private Integer userId;

    private String designerTypeCode;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }
    public String getAptitude() {
        return aptitude;
    }

    public void setAptitude(String aptitude) {
        this.aptitude = aptitude;
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
    public String getDesignerTypeCode() {
        return designerTypeCode;
    }

    public void setDesignerTypeCode(String designerTypeCode) {
        this.designerTypeCode = designerTypeCode;
    }


    @Override
    public String toString() {
        return "DesignerForm{" +
                "id=" + id +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", commonAddress='" + commonAddress + '\'' +
                ", nickname='" + nickname + '\'' +
                ", photo='" + photo + '\'' +
                ", aptitude='" + aptitude + '\'' +
                ", area=" + area +
                ", createTime=" + createTime +
                ", password='" + password + '\'' +
                ", updateTime=" + updateTime +
                ", username='" + username + '\'' +
                ", sexType=" + sexType +
                ", qualifyScore=" + qualifyScore +
                ", roleCode=" + roleCode +
                ", introduction='" + introduction + '\'' +
                ", code=" + code +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", designerTypeCode='" + designerTypeCode + '\'' +
                '}';
    }
}