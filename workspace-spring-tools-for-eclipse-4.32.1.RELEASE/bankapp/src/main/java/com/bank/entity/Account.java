package com.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Account number is required")
    @Column(name = "account_number", unique = true, nullable = false)
    private Integer accountNumber;

    @NotBlank(message = "Account holder name is required")
    @Column(name = "account_holder_name", nullable = false)
    private String accountHolderName;

    @Min(value = 0, message = "Balance must be 0 or greater")
    @Column(nullable = false)
    private double balance;

    public Account() {}

    public Account(Integer accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    // Getters and setters

    public Long getId() { return id; }
    public Integer getAccountNumber() { return accountNumber; }
    public void setAccountNumber(Integer accountNumber) { this.accountNumber = accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public void setAccountHolderName(String accountHolderName) { this.accountHolderName = accountHolderName; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return "Account [accountNumber=" + accountNumber + ", balance=" + balance + ", accountHolderName=" + accountHolderName + "]";
    }
}
