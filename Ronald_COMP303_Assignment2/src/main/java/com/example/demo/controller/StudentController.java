package com.example.demo.controller;

import com.example.demo.model.Program;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.example.demo.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProgramService programService;

    // Registration Page
    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "index";  // The index page with registration and login
    }

    // Register New Student
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        studentService.registerStudent(student);
        return "redirect:/login";
    }

    // Login Page (handled by Spring Security)
    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Spring Security will take care of this
    }

    // After login, show program selection
    @GetMapping("/programs")
    public String showPrograms(Model model) {
        List<Program> programs = programService.getAllPrograms();
        model.addAttribute("programs", programs);
        return "program-selection";
    }

    // Process Program Selection
    @PostMapping("/checkout")
    public String checkout(@RequestParam("programCode") String programCode, Model model) {
        Program selectedProgram = programService.getProgramByCode(programCode);
        model.addAttribute("program", selectedProgram);
        return "checkout";  // Checkout page for payment
    }

    // Payment Page (payment logic handled here)
    @PostMapping("/payment")
    public String processPayment(@RequestParam("cardNumber") String cardNumber, @RequestParam("expiryDate") String expiryDate, @RequestParam("cvv") String cvv, Model model) {
        // Payment logic here (For demo purposes, we'll just show success)
        model.addAttribute("paymentSuccess", true);
        return "payment-confirmation";  // Show payment confirmation page
    }

    // Profile Page (Get student's personal info)
    @GetMapping("/profile")
    public String showProfile(Model model) {
        // Fetch currently logged in student (assuming username is used as a unique identifier)
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = studentService.getStudentByUsername(username);
        model.addAttribute("student", student);
        return "profile";
    }

    // Update Profile
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute Student student) {
        studentService.updateStudentProfile(student);
        return "redirect:/profile";
    }
}
