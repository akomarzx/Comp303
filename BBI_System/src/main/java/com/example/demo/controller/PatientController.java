package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ApiResponseEntity;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.patient.PatientDTO;
import com.example.demo.dto.patient.UpsertPatientDTO;
import com.example.demo.service.PatientService;
import com.example.demo.utility.ValidationGroups.Create;
import com.example.demo.utility.ValidationGroups.Update;

import jakarta.transaction.Transactional;
/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
@RestController
@RequestMapping("/patient")
public class PatientController {
	
	private final PatientService patientService;
	
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@GetMapping
	public ResponseEntity<ApiResponseEntity<List<PatientDTO>>> getAllPatients() {
		List<PatientDTO> patients = this.patientService.getAllPatient();
		ApiResponseEntity<List<PatientDTO>> response = new ApiResponseEntity<>(patients, patients.size(),"");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseEntity<PatientDTO>> getPatient(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
		PatientDTO patient;
		try {
			patient = this.patientService.getPatientById(id, userDetails.getUsername());
			ApiResponseEntity<PatientDTO> response = new ApiResponseEntity<>(patient, 1,"");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			ApiResponseEntity<PatientDTO> response = new ApiResponseEntity<>(null, null,e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping
	public ResponseEntity<MessageResponse> createPatient(@Validated(Create.class) @RequestBody UpsertPatientDTO upsertPatientDTO,
			@AuthenticationPrincipal UserDetails userDetails) {
		try {
			this.patientService.createPatient(upsertPatientDTO, userDetails.getUsername());
			return new ResponseEntity<>(new MessageResponse("Patient Successfully Created"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	ResponseEntity<MessageResponse> updatePatient(@PathVariable Long id, @Validated(Update.class) @RequestBody UpsertPatientDTO upsertPatientDTO,
			@AuthenticationPrincipal UserDetails userDetails) {
		try {
			this.patientService.updatePatient(id, upsertPatientDTO, userDetails.getUsername());
			return new ResponseEntity<>(new MessageResponse("Patient Successfully Updated"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deletePatientInfo(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
		try {
			this.patientService.deletePatientById(id, userDetails.getUsername());
		} catch (Exception e) {
			return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
