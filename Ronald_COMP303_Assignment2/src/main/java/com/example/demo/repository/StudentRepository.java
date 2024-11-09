package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Student;
/* Ronald Ombao
 * 301213219
 * November 09, 2024
 * */
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);
}
