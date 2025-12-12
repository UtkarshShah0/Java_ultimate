package com.boot.security.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.boot.security.model.MyUser;
import com.boot.security.repository.UserRepository;

import lombok.experimental.var;
import org.springframework.security.core.userdetails.User;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<MyUser> user = repository.findByUsername(username);
		System.out.println(user.get().getUsername());
		return  user.map(MyUserDetails::new)
				.orElseThrow(()-> 
				new UsernameNotFoundException("user not found "+ username));
		}
		
}
