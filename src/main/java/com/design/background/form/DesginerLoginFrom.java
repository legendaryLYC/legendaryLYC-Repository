package com.design.background.form;


/**
 * 设计方注册，前后端交互的临时类
 *  时间:2019/2/16
 * 最后修改时间:2019/2/16
 * 注意事项:无
* @author 宋博通
*
*/
public class DesginerLoginFrom {
	private String tel;
	
	private String code;
	
	private String password;
	
	private String confirmPassword;
	
	private String idcode;
	
	private String bankcardnum;
	
	private String name;
	
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankcardnum() {
		return bankcardnum;
	}

	public void setBankcardnum(String bankcardnum) {
		this.bankcardnum = bankcardnum;
	}

	@Override
	public String toString() {
		return "ProjectLoginForm [tel=" + tel + ", code=" + code + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", idcode=" + idcode + ", bankcardnum=" + bankcardnum + ", name=" + name + "]";
	}
	public DesginerLoginFrom() {
		super();
	}

	public DesginerLoginFrom(String tel, String code, String password, String confirmPassword, String idcode,
			String bankcardnum,String name,String email) {
		super();
		this.tel = tel;
		this.code = code;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.idcode = idcode;
		this.bankcardnum = bankcardnum;
		this.name = name;
		this.email = email;
	}

}
