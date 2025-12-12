package com.tcs.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@SpringBootApplication
@PropertySources(value =
{
		@PropertySource("application-dev.properties"),
		@PropertySource("application-prod.properties")})

public class FirstRestApplication{
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext con = SpringApplication.run(FirstRestApplication.class, args);
		Environment env = con.getEnvironment();
		System.out.println("----------------------");
		System.out.println(env.getProperty("tcs"));
		
		
	}
	
	


}
