package com.tcs.jwtsecurity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SEC_USER")
public class User {
    @Id
    private int id;
	private String userName;
    private String password;
    private String roles;
    public String getUserName() {
    	return userName;
    }
    public void setUserName(String userName) {
    	this.userName = userName;
    }
    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    public String getRoles() {
    	return roles;
    }
    public void setRoles(String roles) {
    	this.roles = roles;
    }
    public int getId() {
    	return id;
    }
}