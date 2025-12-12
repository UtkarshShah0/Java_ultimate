package com.gateway.res4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
	
	@RequestMapping("/myfallback")
	public ResponseEntity<String> fallBackMethod() {
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Please try after some time");
	}
}
