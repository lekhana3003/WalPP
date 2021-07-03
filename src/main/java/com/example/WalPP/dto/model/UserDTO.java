package com.example.WalPP.dto.model;

public class UserDTO {
    private Integer userId;
    private Float intialBalance;
    private Float balance;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Float getIntialBalance() {
        return intialBalance;
    }

    public void setIntialBalance(Float intialBalance) {
        this.intialBalance = intialBalance;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }


}
