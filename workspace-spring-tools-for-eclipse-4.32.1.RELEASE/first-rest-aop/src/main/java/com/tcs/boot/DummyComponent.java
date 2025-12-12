package com.tcs.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Profile(value = {"prod", "dev"})

public class DummyComponent {


	@Value("${sample.data}")
	String data;
	
	@Value("${server.port}")
	String port;
	

	
	
	
	
	public DummyComponent() {
		System.out.println(data);

	}
	
	@PostConstruct
	public void method() {
//		System.out.println(p);
		System.out.println(data);
		System.out.println(port);
	}
}
