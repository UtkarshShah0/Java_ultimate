package com.tcs.res4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HelloserviceRes4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloserviceRes4jApplication.class, args);
	}

}
