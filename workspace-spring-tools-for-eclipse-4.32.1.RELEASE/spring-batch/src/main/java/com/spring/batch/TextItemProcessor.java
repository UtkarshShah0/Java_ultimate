package com.spring.batch;

import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class TextItemProcessor implements ItemProcessor<String, String> {

	public TextItemProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String meassage) throws Exception {

		System.out.println("from processor.....");
		String encryptedMessage = meassage.replaceAll("\\d", "*");
		return encryptedMessage;
	}

}