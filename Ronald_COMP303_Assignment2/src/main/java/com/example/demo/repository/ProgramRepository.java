package com.example.demo.repository;

import com.example.demo.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
/* Ronald Ombao
 * 301213219
 * November 09, 2024
 * */
public interface ProgramRepository extends JpaRepository<Program, Long> {
    Program findByProgramCode(String programCode);
}
