package com.tcs.boot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcs.boot.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
	List<Loan> findByBorrowerNamelike(String patternString);

	List<Loan> findByBorrowerNameOrderByBorrowerName(String name);

	List<Loan> findByLoanStatus(boolean loanStatus);

	List<Loan> findByTenureLessThan(Long loanId);

	List<Loan> findByTenureBetween(int tenure1, int tenure2);

	List<Loan> findByDateBorrowedBefore(String dateBorrowed);

	@Query("Select l.borrowerName from Loan l where l.loanId= :id")
	String findBorrowerNameById(@Param("id") Long id);
	
	@Query("Select l.borrowerName, l.balanceEMI from Loan l where l.loanId = :id")
	String findByBorrowerNameAndBalanceEMIById(@Param("id") Long id);
	
	@Query(
	        value = "SELECT * FROM Loan l where l.tenure = :tenure AND l.BalanceEMI = :balEMI", 
	        nativeQuery=true
	    )
	    public Optional<Loan> findByTenureAndBalanceEMI(@Param("tenure") int tenure, 
	                                                    @Param("balEMI") int balEMI);




}
















