package com.example.demo.dto;

public record ApiResponseEntity<T>(T result, String message) {
}
