package com.spring.batch;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.FlatFileItemWriter;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class SpringBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchApplication.class, args);
	}

	@Bean
	protected FlatFileItemReader<String> reader() {

		return new FlatFileItemReaderBuilder<String>().resource(new ClassPathResource("data.csv"))
				.name("csv-reader").lineMapper((line, lineNumber) -> line).build();
	}

	@Bean
	protected FlatFileItemWriter<String> writer() {
		String fileLoactionString = "src/main/resources/encrypted.csv";
		return new FlatFileItemWriterBuilder<String>().name("csv-writer")
				.resource(new FileSystemResource(fileLoactionString)).lineAggregator(item -> item).build();

	}

	@Bean
	protected Step encryptionStep(JobRepository jobRepo, PlatformTransactionManager manager,
			FlatFileItemReader<String> reader, TextItemProcessor processor, FlatFileItemWriter<String> writer) {
		System.out.println("from step " + processor.toString());
		return new StepBuilder("encryption-step", jobRepo).<String, String>chunk(2, manager).reader(reader)
				.processor(processor).writer(writer).build();

	}

	@Bean
	protected Job maskingJob(JobRepository jobRepository, Step encryptionStep, BatchProcessingListener listener) {
		System.out.println("from job");
		return new JobBuilder("encryption-job", jobRepository).start(encryptionStep).listener(listener).build();
	}

}
