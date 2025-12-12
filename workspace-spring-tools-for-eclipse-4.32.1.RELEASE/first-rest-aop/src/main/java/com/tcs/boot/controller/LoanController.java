package com.tcs.boot.controller;

import java.util.List;
import org.springframework.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.boot.entity.Loan;
import com.tcs.boot.service.LoanService;

@RestController
@RequestMapping("/loan/api/v1.0")
public class LoanController {

	@Autowired
	LoanService service;

	// http://localhost:8090/loan/api/v1.0/create
	// JAXB
//	@PostMapping(value ="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public Loan loanApplication(@RequestBody Loan loan) {
//		return service.addLoan(loan);
//	}
	@PostMapping(value ="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Loan> loanApplication(@RequestBody Loan loan) {
		
//		return service.addLoan(loan);
		
//		return new ResponseEntity<Loan>(HttpStatus.CREATED);
		Loan newLoan = service.addLoan(loan);
//		return new ResponseEntity<Loan>(newLoan, HttpStatus.OK);
		HttpHeaders headers = new HttpHeaders();
		headers.add("xx-created-by", "Utkarsh");
		headers.add("content-type", "application/json"); //MIME type
//		return new ResponseEntity<Loan>(newLoan, headers, HttpStatus.OK);
		return ResponseEntity.ok(newLoan);
	}

	@GetMapping("/fetch/{id}")
	public Loan getLoan(@PathVariable Long id) {
		return service.getLoan(id);
//		return new ResponseEntity<Loan>(HttpStatus.BAD_REQUEST);
	}
	
//	@GetMapping("/fetch/{id}")
//	public ResponseEntity<Loan> getLoan(@PathVariable Long id) {
//		return new ResponseEntity<Loan>(HttpStatus.BAD_REQUEST);
//		
//	}

	@GetMapping("/all")
	public List<Loan> getLoans() {
		return service.getLoans();
	}

	@PutMapping("/modify") // for full update Patch request
	public Loan doUpdate(@RequestBody Loan loan) {
		return service.update(loan);
	}

	@PutMapping("/modify2") // for full update Patch request
	public Loan doUpdate2(@RequestBody Loan loan) {
		return service.update2(loan);
	}

//	@DeleteMapping("/remove/{lid}")
//	public void delete(@PathVariable Long lid) {
//		service.remove(lid);
//	}
	@DeleteMapping("/remove/{lid}")
	public ResponseEntity<Void> delete(@PathVariable Long lid) {
		service.remove(lid);
		
		return new ResponseEntity<>(HttpStatusCode.valueOf(204));
	}

}
