package com.jpa.many2many;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa.many2many.entity.Post;
import com.jpa.many2many.entity.Tag;
import com.jpa.many2many.repository.PostRepository;

@SpringBootApplication
public class JpaManyToManyApplication implements CommandLineRunner{

	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PostRepository tagRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaManyToManyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
	
	
//	Post post = new Post("JPA Hibernate Many to Many example using Spring Boot",
//			"Learn how to map a many to many relationship using JPA",
//			"Code example with Java Maven");
//	
//	Tag tag1 = new Tag("Spring Boot");
//	Tag tag2 = new Tag("JPA-Hibernate");
//	Tag tag3 = new Tag("Annotation Configuration");
//	
//	post.getTags().add(tag1);
//	post.getTags().add(tag2);
//	post.getTags().add(tag3);
//	
//	postRepository.save(post);
	
	Optional<Post> optional = postRepository.findById(1L);
	Post postRef = optional.isPresent()? optional.get():
					optional.orElseThrow(() -> new NoSuchElementException("id not found"));
	
	System.out.println(postRef);
	System.out.println(postRef.getTitle());
	System.out.println(postRef.getContent());
	System.out.println(postRef.getDescription());
	
	Set<Tag> tags = postRef.getTags();
	for (Tag tag: tags) {
		System.out.println("Tag is "+ tag.getName());
	}
	
	
	
	
	
	
	}
	
	
	
	
	

}
