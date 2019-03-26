package com.design.background.entity;

import java.util.Date;

public class Tcode {
    private Long id;

    private String accountCode;

    private Integer verCode;

    private Date creTime;

    private Integer codeType; // 项目方注册 1000 设计师注册 1001 找回密码 1002

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode == null ? null : accountCode.trim();
    }

    public Integer getVerCode() {
        return verCode;
    }

    public void setVerCode(Integer verCode) {
        this.verCode = verCode;
    }

    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }

	@Override
	public String toString() {
		return "Tcode [id=" + id + ", accountCode=" + accountCode + ", verCode=" + verCode + ", creTime=" + creTime
				+ ", codeType=" + codeType + "]";
	}
    
    
}