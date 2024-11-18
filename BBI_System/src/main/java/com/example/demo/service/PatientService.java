package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.BloodGroup;
import com.example.demo.domain.Patient;
import com.example.demo.domain.User;
import com.example.demo.dto.patient.PatientDTO;
import com.example.demo.dto.patient.UpsertPatientDTO;
import com.example.demo.repository.PatientRepository;

import jakarta.transaction.Transactional;

@Service
public class PatientService {
	
	private final UserService userService;
	private final PatientRepository patientRepository;
	
	public PatientService(PatientRepository patientRepository, UserService userService) {
		this.userService = userService;
		this.patientRepository = patientRepository;
	}
	
	@Transactional
	public List<PatientDTO> getAllPatient() {
		List<Patient> allPatients = this.patientRepository.findAll();
		List<PatientDTO> mappedPatients = new ArrayList<>();
		
		for(Patient patient : allPatients) {
			PatientDTO dto = new PatientDTO(patient.getPatientId(), 
					patient.getUser().getUsername(),
					patient.getFirstName(),
					patient.getLastName(),
					patient.getGender(),
					patient.getPhone(),
					patient.getCity(),
					patient.getBloodGroup().toString());
			
			mappedPatients.add(dto);
		}
		
		return mappedPatients;
	}
	
	@Transactional
	public PatientDTO getPatientById(Long patientId, String username) throws Exception { 
		
		Optional<Patient> patientResult = this.patientRepository.findById(patientId);
		
		if(patientResult.isPresent()) {
			
			Patient patient = patientResult.get();
			
			if(!patient.getUser().getUsername().equals(username)) {
				throw new Exception("Patient Info does not belong to user.");
			}
			
			return new PatientDTO(patient.getPatientId(), 
					patient.getUser().getUsername(),
					patient.getFirstName(),
					patient.getLastName(),
					patient.getGender(),
					patient.getPhone(),
					patient.getCity(),
					patient.getBloodGroup().toString());
					
		} else {
			return null;
		}
		
	}
	@Transactional
	public void createPatient(UpsertPatientDTO patient, String username) throws Exception {
		
		Optional<User> currentUser = this.userService.getUserByUsername(username);
		
		if(currentUser.isPresent() ) {
			
			Optional<Patient> existingInfo = this.patientRepository.findByUser(currentUser.get());
			
			if(existingInfo.isPresent()) {
				throw new Exception("Patient info already exist.");
			}
			
			Patient newPatient = new Patient();
			newPatient.setFirstName(patient.firstName());
			newPatient.setLastName(patient.lastName());
			newPatient.setCity(patient.city());
			newPatient.setGender(patient.gender());
			newPatient.setPhone(patient.phone());
			newPatient.setUser(currentUser.get());
			newPatient.setBloodGroup(BloodGroup.valueOf(patient.bloodGroup()));
			this.patientRepository.save(newPatient);
		} else {
			throw new Exception("User is not found."); 
		}
	}
	
	@Transactional
	public void updatePatient(Long patientId, UpsertPatientDTO patientDto, String username) throws Exception {
		
		Optional<Patient> existingInfo = this.patientRepository.findById(patientId);
			
		if(existingInfo.isPresent()) {
			
			if(!existingInfo.get().getUser().getUsername().equals(username)) {
				throw new Exception("Patient Info does not belong to user.");
			}
			
			Patient patient = existingInfo.get();
			if(patientDto.firstName() != null ) {
				patient.setFirstName(patientDto.firstName());
			}
			if(patientDto.lastName() != null) {
				patient.setLastName(patientDto.lastName());
			}
			if(patientDto.city() != null) {
				patient.setCity(patientDto.city());
			}
			if(patientDto.gender() != null) {
				patient.setGender(patientDto.gender());
			}
			if(patientDto.phone() != null) {
				patient.setPhone(patientDto.phone());
			}
			if(patientDto.bloodGroup() != null) {
				patient.setBloodGroup(BloodGroup.valueOf(patientDto.bloodGroup()));
			}
			
			this.patientRepository.save(patient);
		} else {
			throw new Exception("Patient is not found."); 
		}
	}
	
	@Transactional
	public void deletePatientById(Long patientId, String username) throws Exception {
		
		Optional<Patient> existingInfo = this.patientRepository.findById(patientId);
		
		if(existingInfo.isPresent()) {
			
			if(!existingInfo.get().getUser().getUsername().equals(username)) {
				throw new Exception("Patient Info does not belong to user.");
			}
			
			this.patientRepository.deleteById(patientId);
		}
	}
	
}
