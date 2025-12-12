package com.tcs.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.boot.entity.Loan;
import com.tcs.boot.repository.LoanRepository;

@Service
public class LoanService {
	
	@Autowired
	LoanRepository repository;
	
	
	public Loan addLoan(Loan loan) {
		
		return repository.save(loan);
	}
	
	
	
	public Loan getLoan(Long loanId) {
		
		Optional<Loan> optionalLoan = repository.findById(loanId);
		return optionalLoan.get();
	}
	
	public List<Loan> getLoans(){
		return repository.findAll();
	}
	
	
	public Loan update(Loan loan) {
		return repository.save(loan);
	}
	
	public Loan update2(Loan loan) {
		Optional<Loan> optional = repository.findById(loan.getLoanId());
		
		Loan tempLoan = optional.get();
		
		if (loan.getBorrowerName() != null) {
			tempLoan.setBorrowerName(loan.getBorrowerName());
		}
		
		if (loan.getDateBorrowed() != null) {
			tempLoan.setDateBorrowed(loan.getDateBorrowed());
		}
		
//		if ((Integer)(loan.getTenure()) != null) {
//			
//		}
		if (tempLoan.getTenure() != loan.getTenure()) {
			tempLoan.setTenure(loan.getTenure());
		}
		
		if (tempLoan.getAmount() != loan.getAmount()) {
			tempLoan.setAmount(loan.getAmount());
		}
		
		
		
		return repository.save(tempLoan);
	}
	
	
	
	
	
	
	public void remove(Long lid) {
		Optional<Loan> loan = repository.findById(lid);
		
		if(loan.isPresent()) 
			repository.delete(loan.get());
		else
			throw new IllegalArgumentException(lid + " not found! ");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
