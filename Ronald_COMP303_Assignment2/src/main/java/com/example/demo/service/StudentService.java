package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Register student
    public void registerStudent(Student student) {
        studentRepository.save(student);
    }

    // Get student by username (useful for login)
    public Student getStudentByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    // Update student profile
    public void updateStudentProfile(Student student) {
        studentRepository.save(student);
    }
}
