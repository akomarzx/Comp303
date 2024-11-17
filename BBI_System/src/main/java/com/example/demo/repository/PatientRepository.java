package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Patient;
import com.example.demo.domain.User;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	Optional<Patient> findByUser(User ownerUser);
}
