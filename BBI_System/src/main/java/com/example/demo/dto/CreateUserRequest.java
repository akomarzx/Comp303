package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
public record CreateUserRequest(@NotNull(message = "username cannot be null")String username, 
								@NotNull(message = "password cannot be null")String password, 
								Boolean isBloodBank) {

}
