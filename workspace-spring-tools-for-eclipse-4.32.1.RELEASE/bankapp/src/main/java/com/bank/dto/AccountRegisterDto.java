package com.bank.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountRegisterDto {

    @NotNull(message = "Account number is required")
    private Integer accountNumber;

    @NotBlank(message = "Account holder name is required")
    private String accountHolderName;

    @Min(value = 0, message = "Initial balance cannot be negative")
    private double balance;

    public AccountRegisterDto() {}

    // getters & setters
    public Integer getAccountNumber() { return accountNumber; }
    public void setAccountNumber(Integer accountNumber) { this.accountNumber = accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public void setAccountHolderName(String accountHolderName) { this.accountHolderName = accountHolderName; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
