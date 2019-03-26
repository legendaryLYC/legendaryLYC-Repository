package com.design.background.entity;

import java.math.BigDecimal;

public class UserAccount {
    private Long id;

    private Long userId;

    private String name;

    private BigDecimal payAmount;

    private BigDecimal balance;

    private BigDecimal extract;

    public Long getId() {
        return id;
    }

    public UserAccount setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public UserAccount setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserAccount setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public UserAccount setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public UserAccount setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public BigDecimal getExtract() {
        return extract;
    }

    public UserAccount setExtract(BigDecimal extract) {
        this.extract = extract;
        return this;
    }
}