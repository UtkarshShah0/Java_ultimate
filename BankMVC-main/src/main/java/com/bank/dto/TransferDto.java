package com.bank.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class TransferDto {
    @NotNull
    private Long fromAccountNumber;
    @NotNull
    private Long toAccountNumber;

    @Min(value = 1, message = "Amount must be at least 1")
    private double amount;

    public Long getFromAccountNumber() { return fromAccountNumber; }
    public void setFromAccountNumber(Long fromAccountNumber) { this.fromAccountNumber = fromAccountNumber; }
    public Long getToAccountNumber() { return toAccountNumber; }
    public void setToAccountNumber(Long toAccountNumber) { this.toAccountNumber = toAccountNumber; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
