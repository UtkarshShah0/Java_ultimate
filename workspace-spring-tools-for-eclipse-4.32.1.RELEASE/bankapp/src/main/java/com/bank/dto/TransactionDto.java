package com.bank.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TransactionDto {

    @NotNull(message = "Account number is required")
    private Integer accountNumber;

    @Min(value = 1, message = "Amount must be positive")
    private double amount;

    public TransactionDto() {}

    // getters & setters
    public Integer getAccountNumber() { return accountNumber; }
    public void setAccountNumber(Integer accountNumber) { this.accountNumber = accountNumber; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
