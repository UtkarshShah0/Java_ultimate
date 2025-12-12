package com.gateway.res4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayRes4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayRes4jApplication.class, args);
	}

}
