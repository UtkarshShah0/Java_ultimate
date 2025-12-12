package com.boot.security.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.relation.Role;

//import org.springframework.data.jpa.repository.query.EqlParser.New_valueContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.boot.security.model.MyUser;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;

	public MyUserDetails(MyUser user) {

		username = user.getUsername();
		password = user.getPassword();
		authorities = Arrays.stream(user.getRole().split(","))
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // for
																														// //
																														// multiple
																														// //
																														// code
				.collect(Collectors.toList());
		// String role = "ADMIN,USER,OPS,HR"
		// CONVERTING A COMMA SEPARATED STRING INTO AN ARRAYLIST

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		// return Collections.singleton(new SimpleGrantedAuthority("USER")); use this
		// when we have only one type of role for all users
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
