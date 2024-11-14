package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<MessageResponse> register(@Valid @RequestBody CreateUserRequest createUserRequest) {
		
		ResponseEntity<MessageResponse> response;
		MessageResponse message;
		
		try {
			this.userService.registerNewUser(createUserRequest);
			response = ResponseEntity.ok(new MessageResponse("User successfully registered - " + createUserRequest.username()));
		} catch (Exception ex) {
			response = new ResponseEntity<>(new MessageResponse("Error Encountered during Registration - " + ex.getMessage()), HttpStatus.BAD_REQUEST);
		}
		
		return response;
	}
	
}
