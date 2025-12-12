package com.tcs.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component

public class DummyComponent2 {

	
	@Autowired
	Environment env;
	
//	@Value("${db_password}")
//	String dbp;
	
	
	public DummyComponent2() {
		
	}
	
	@PostConstruct
	public void method() {
		System.out.println(env.getProperty("db_password"));
	}


}
