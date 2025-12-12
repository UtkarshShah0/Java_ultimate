package com.boot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
public class BootSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootSecurityApplication.class, args);
	}
	
//	@GetMapping("/connect")
	public String getConnection() {
		return "connected";
	}
}
