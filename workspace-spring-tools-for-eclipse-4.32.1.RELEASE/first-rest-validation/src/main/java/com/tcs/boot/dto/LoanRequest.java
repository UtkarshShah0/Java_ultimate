package com.tcs.boot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoanRequest {
	
	@NotNull(message="borrower name shouldn't be null")
	@NotBlank(message="borrower name shouldn't be blank")
	String borrowerName;
	String dateBorrowed;
	
	@Min(value = 12, message="Tenure can't be less than 12")
	@Max(value = 50, message="Tenure can't be more than 48")
	int tenure;
	
	int balanceEMI;
	boolean loanStatus;
	
	@NotNull
	@Min(value = 1, message="Value can't be less than 1")
	@Max(value = 1000000, message="Value can't be more than 1000000")
	double amount;
	
	
	
	
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public int getBalanceEMI() {
		return balanceEMI;
	}
	public void setBalanceEMI(int balanceEMI) {
		this.balanceEMI = balanceEMI;
	}
	public boolean isLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(boolean loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getDateBorrowed() {
		return dateBorrowed;
	}
	public void setDateBorrowed(String dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}

