package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.config.PasswordEncoderConfig;
import com.example.demo.domain.Roles;
import com.example.demo.domain.User;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoderConfig encoder;
	
	public UserService(UserRepository userRepository, PasswordEncoderConfig encoder) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}
	
	public Optional<User> getUserByUsername(String userName) {
		return this.userRepository.findUserByUsername(userName);
	}
	
	public void registerNewUser(CreateUserRequest createUserRequest) {
		User newUser = new User();
		newUser.setUsername(createUserRequest.username());
		newUser.setPassword(encoder.passwordEncoder().encode(createUserRequest.password()));
		newUser.setRole(createUserRequest.isBloodBank() != null && createUserRequest.isBloodBank() ? Roles.BLOODBANK : Roles.SEEKER);
		this.userRepository.save(newUser);
	}
	
	public void registerAdmin(CreateUserRequest createUserRequest) {
		User newUser = new User();
		newUser.setUsername(createUserRequest.username());
		newUser.setPassword(encoder.passwordEncoder().encode(createUserRequest.password()));
		newUser.setRole(Roles.ADMIN);
		this.userRepository.save(newUser);
	}
}
