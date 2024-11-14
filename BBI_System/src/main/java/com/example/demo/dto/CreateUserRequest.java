package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(@NotNull(message = "username cannot be null")String username, 
								@NotNull(message = "password cannot be null")String password, 
								Boolean isBloodBank) {

}
