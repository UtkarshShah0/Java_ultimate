package com.boot.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/root")
public class HomeController {
	
	@GetMapping("/all")
	public String commonEndPoint() {
		
		return "Welcome";
	}
	
	@GetMapping("/inbox")
	public String userEndPoint(HttpServletRequest request) {
		
		return "Welcome User" + request.getSession().getId();
	}
	
	@GetMapping("/management")
	public String adminEndPoint(HttpServletRequest request) {
		
		return "Welcome Admin"+ request.getSession().getId();
	}
}
