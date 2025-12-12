package com.tcs.jwtsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.jwtsecurity.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUserName(String username);
}
