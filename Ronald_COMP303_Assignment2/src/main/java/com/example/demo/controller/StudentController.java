package com.example.demo.controller;

import com.example.demo.model.Enrollment;
import com.example.demo.model.Program;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import com.example.demo.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
/* Ronald Ombao
 * 301213219
 * November 09, 2024
 * */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ProgramService programService;

    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "index";  
    }
    
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        studentService.registerStudent(student);
        return "redirect:/";
    }

    @GetMapping("/programs")
    public String showPrograms(Model model) {
        List<Program> programs = programService.getAllPrograms();
        model.addAttribute("programs", programs);
        return "programs";
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam String programCode, Model model) {
        Program selectedProgram = programService.getProgramByCode(programCode);
        model.addAttribute("program", selectedProgram);
        model.addAttribute("programCode", programCode);  
        return "checkout";
    }

    @PostMapping("/payment")
    public String processPayment(@RequestParam String cardNumber, 
                                 @RequestParam String expiryDate, 
                                 @RequestParam String cvv, 
                                 @RequestParam String programCode,  
                                 Model model) {
        // Retrieve the selected program
        Program selectedProgram = programService.getProgramByCode(programCode);

        // Get current authenticated user (the student)
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = studentService.getStudentByUsername(username);

        // Create the enrollment object
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);  
        enrollment.setProgram(selectedProgram); 
        enrollment.setAmountPaid(selectedProgram.getFee());  
        enrollment.setStatus("Paid");  
        enrollment.setStartDate(LocalDate.now());  

        // Save the enrollment
        studentService.createEnrollment(enrollment);

        // Add attributes to the model to display the payment confirmation
        model.addAttribute("paymentSuccess", true);
        model.addAttribute("enrollment", enrollment);
        return "payment-confirmation"; 
    }
  

    @GetMapping("/profile")
    public String showProfile(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Student student = studentService.getStudentByUsername(username);
        
        List<Program> enrolledPrograms = studentService.getEnrolledProgramsForStudent(student.getStudentId());

        model.addAttribute("student", student);
        model.addAttribute("enrolledPrograms", enrolledPrograms);
        
        return "profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute Student student) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        studentService.updateStudentProfile(student, username);
        return "redirect:/profile";
    }
}
