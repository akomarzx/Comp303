/**
 * 
 */
package com.example.demo.dto.patient;

/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
public record PatientDTO(Long patientId, 
		String username, 
		String firstName, 
		String lastName,
		String gender,
		String phone,
		String city,
		String bloodGroup) {
}
