package com.jpa.one2many;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jpa.one2many.entity.Book;
import com.jpa.one2many.entity.Page;
import com.jpa.one2many.repository.BookRepository;
import com.jpa.one2many.repository.PageRepository;

@SpringBootApplication
public class JpaOneToManyApplication {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PageRepository pageRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaOneToManyApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(BookRepository bookRepository, PageRepository pageRepository) {
		return (args) -> {

//			Book book = new Book("Spring", "Rod Johnson", "3435");
//			
//			Page p1 = new Page(1, "Introduction", "IOC Dependency", book);
//			Page p65 = new Page(65, "Java 8 contents", "Java 8", book);
//			Page p95 = new Page(95, "Concurrency", "Virtual Threads", book);
//			
//			book.getPages().add(p1);
//			book.getPages().add(p65);
//			book.getPages().add(p95);
//			Book book = new Book("Python", "Guido Von Rossum", "1000");
//
//			Page p29 = new Page(29, "NumPy", "DataFrame", book);
//			Page p69 = new Page(69, "Multithreading", "Parallel Processing", book);
//			Page p102 = new Page(102, "GenAI", "Ollama", book);
//
//			book.getPages().add(p29);
//			book.getPages().add(p69);
//			book.getPages().add(p102);
//
//			bookRepository.save(book);
			
			
			Iterable<Page> pages = pageRepository.findAll();
			for (Page p: pages)
				System.out.println(p);
			
			Optional<Page> tempPage = pageRepository.findById(1l);
			System.out.println(tempPage.get());
			
		
		};
	}

}
 