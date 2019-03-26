package com.design.background.form;

import java.math.BigDecimal;

public class MyAccountForm {

	  private Long id;
	  
	  private String username;

	  private String tel;
	  
	  private Long userId;

      private String name;

      private BigDecimal payAmount;

      private BigDecimal balance;
      
      private BigDecimal extract;

      public Long getId() {
          return id;
      }

      public void setId(Long id) {
          this.id = id;
      }

      
      public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTel() {
          return tel;
      }

      public void setTel(String tel) {
          this.tel = tel == null ? null : tel.trim();
      }

   
      public Long getUserId() {
          return userId;
      }

      public void setUserId(Long userId) {
          this.userId = userId;
      }

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name == null ? null : name.trim();
      }

      public BigDecimal getPayAmount() {
          return payAmount;
      }

      public void setPayAmount(BigDecimal payAmount) {
          this.payAmount = payAmount;
      }

      public BigDecimal getBalance() {
          return balance;
      }

      public void setBalance(BigDecimal balance) {
          this.balance = balance;
      }
      
      public BigDecimal getExtract() {
          return extract;
      }

      public void setExtract(BigDecimal extract) {
          this.extract = extract;
      }

	@Override
	public String toString() {
		return "MyAccountForm [id=" + id + ", username=" + username + ", tel=" + tel + ", userId=" + userId + ", name="
				+ name + ", payAmount=" + payAmount + ", balance=" + balance + ", extract=" + extract + "]";
	}



	
}
