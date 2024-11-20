/**
 * 
 */
package com.example.demo.dto.patient;

import com.example.demo.utility.ValidationGroups.Create;

import jakarta.validation.constraints.NotNull;

/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
@NotNull
public record UpsertPatientDTO( 
		@NotNull(groups = Create.class)String firstName, 
		@NotNull(groups = Create.class)String lastName,
		@NotNull(groups = Create.class)String gender,
		@NotNull(groups = Create.class)String phone,
		@NotNull(groups = Create.class)String city,
		@NotNull(groups = Create.class)String bloodGroup) {
}
