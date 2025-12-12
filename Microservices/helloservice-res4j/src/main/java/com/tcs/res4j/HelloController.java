package com.tcs.res4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class HelloController {
	
	@Value("${spring.application.name}")
	String str;
	
	@Autowired
	Environment environment;
	
	@GetMapping("/props")
	public String welcome() {
		String port = environment.getProperty("server.port");
		return "Programming Microservices @ "+ port + " Applicatgion name "+ str;
	}
	
	@GetMapping("/status")
	public String status() {
		return "Up and Running ->>";
	} 
	
	@GetMapping("/")
	public String status2() {
		return "Status2 Up and Running ->>";
	} 
	
}
