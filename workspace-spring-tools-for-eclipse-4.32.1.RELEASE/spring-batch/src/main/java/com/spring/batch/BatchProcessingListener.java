package com.spring.batch;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class BatchProcessingListener implements JobExecutionListener {
	
	public void afterJob(JobExecution jobExecution) {
		 if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			 
			 String filePathString = "src/main/resources/encrypted.csv";
			 try {
				           Files.lines(Paths.get(filePathString)).forEach(System.out::println);
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
		 }
		
	}
	

	

}