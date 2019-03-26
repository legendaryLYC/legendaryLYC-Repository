package com.design.background.form;


/**
 * 项目方注册，前后端交互的临时类
 *  时间:2019/2/14
 * 最后修改时间:2019/2/14
 * 注意事项:无
* @author 周天
*
*/
public class ProjectLoginForm {
	private String tel;
	
	private String code;
	
	private String password;
	
	private String confirmPassword;
	
	private String account;
	
	private String name;
	
	private String certificate;
	
	private String email;

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ProjectLoginForm [tel=" + tel + ", code=" + code + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", account=" + account + ", name=" + name + ", certificate=" + certificate
				+ ", email=" + email + "]";
	}




}
