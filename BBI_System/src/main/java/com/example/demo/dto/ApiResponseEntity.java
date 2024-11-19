package com.example.demo.dto;

public record ApiResponseEntity<T>(T result, Integer count, String message) {
}
