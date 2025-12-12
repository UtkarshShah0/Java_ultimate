package com.boot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.security.model.MyUser;
import com.boot.security.repository.UserRepository;

@RestController
@RequestMapping("/register")
public class RegisterationController {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	PasswordEncoder encoder;
	
	
	@PostMapping("/add")
	public MyUser newUser(@RequestBody MyUser user) {
		user.setPassword(encoder.encode(user.getPassword())); //encoding password
		return repository.save(user);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
