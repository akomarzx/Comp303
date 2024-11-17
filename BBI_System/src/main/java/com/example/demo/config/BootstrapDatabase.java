package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;

/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
@Entity
public class BootstrapDatabase implements CommandLineRunner {
	
	private final UserService userService;
	
	public BootstrapDatabase(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		
		CreateUser
		
		this.userService.registerNewUser(null);
		
	}
	
}
