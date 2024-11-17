/**
 * 
 */
package com.example.demo.dto.patient;

/**
 * 
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
