package com.example.demo.dto;
/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
public record ApiResponseEntity<T>(T result, Integer count, String message) {
}
