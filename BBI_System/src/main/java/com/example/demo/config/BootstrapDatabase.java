package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.service.UserService;

/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
@Configuration
public class BootstrapDatabase implements CommandLineRunner {
	
	private final UserService userService;
	
	public BootstrapDatabase(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void run(String... args) throws Exception {
		
	}
	
}
