package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	public List<Enrollment> findByStudentId(Long studentId);
}
