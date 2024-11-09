package com.example.demo.service;

import com.example.demo.model.Enrollment;
import com.example.demo.model.Program;
import com.example.demo.model.Student;
import com.example.demo.repository.EnrollmentRepository;
import com.example.demo.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/* Ronald Ombao
 * 301213219
 * November 09, 2024
 * */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired 
    EnrollmentRepository enrollmentRepository;
    
    public void registerStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    // Update student profile
    public void updateStudentProfile(Student student, String username) {
    	Student updatedStudent = this.studentRepository.findByUsername(username);
    	updatedStudent.setAddress(student.getAddress());
    	updatedStudent.setFirstName(student.getFirstName());
    	updatedStudent.setLastName(student.getLastName());
    	updatedStudent.setPostalCode(student.getPostalCode());
        studentRepository.save(updatedStudent);
    }
    
    public List<Program> getEnrolledProgramsForStudent(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        List<Program> programs = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            programs.add(enrollment.getProgram());
        }
        return programs;
    }
    
    public void createEnrollment(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }
}
