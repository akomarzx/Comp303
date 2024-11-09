package com.example.demo.repository;

import com.example.demo.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    Program findByProgramCode(String programCode);
}
