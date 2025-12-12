package com.tcs.boot.controller;

import org.springframework.http.*;

import java.util.List;

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
import org.springframework.web.client.RestTemplate;

import com.tcs.boot.entity.Loan;

@RestController
@RequestMapping("/client/loan/api/v1.0")
public class LoanController {

	@Autowired
	RestTemplate template;

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Loan> loanApplication(@RequestBody Loan loan) {

		return null;
	}

	@GetMapping("/fetch/{id}")
	public ResponseEntity<Loan> getLoan(@PathVariable Long id) {

		String urlString = "http://localhost:9999/loan/api/v1.0/fetch/"+id;
		Loan loan = template.getForObject(urlString, Loan.class);

		return ResponseEntity.ok(loan);
	}

	@GetMapping("/all")
	public List<Loan> getLoans() {
		return null;
	}

	@PutMapping("/modify")
	public Loan doUpdate(@RequestBody Loan loan) {
		return null;
	}

	@PutMapping("/modify2")
	public Loan doUpdate2(@RequestBody Loan loan) {
		return null;
	}

	@DeleteMapping("/remove/{lid}")
	public ResponseEntity<Void> delete(@PathVariable Long lid) {

		return null;
	}

}
