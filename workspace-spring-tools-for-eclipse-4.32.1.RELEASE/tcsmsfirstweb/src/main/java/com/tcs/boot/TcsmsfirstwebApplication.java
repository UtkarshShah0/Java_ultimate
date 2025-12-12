package com.tcs.boot;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TcsmsfirstwebApplication{

//    private final HelloController helloController;
	
//	@Autowired
//	ProductRepository repository;

//    TcsmsfirstwebApplication(HelloController helloController) {
//        this.helloController = helloController;
//    }
	
	public static void main(String[] args) {
		SpringApplication.run(TcsmsfirstwebApplication.class, args);
	}


//	public void run(ApplicationArguments args) throws Exception {
		
//		Product p1 = new Product();
//		p1.setPid(108);
//		p1.setDescription("Charger");
//		p1.setPurchasedOn(new Date(2025,11,06));
//		p1.setPurchasedOn(new Date(2025,11,06));
//		p1.setQty(15);
//		p1.setPrice(250);
//		
//		repository.save(p1);
//		
//		System.out.println(p1);
		
//		ConfigurableApplicationContext context = SpringApplication.run(TcsmsfirstwebApplication.class);
//		String beans[] = context.getBeanDefinitionNames();
//		for(String b:beans) {
//			System.out.println(b);
//		}
//		System.out.println(beans.length);
//		
//	}

}
