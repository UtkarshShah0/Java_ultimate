package com.tcs.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FirstRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstRestApplication.class, args);
	}
	
	
	@Bean
	@Scope("singleton")
	RestTemplate createTemplate() {
		return new RestTemplate();
	}

}
