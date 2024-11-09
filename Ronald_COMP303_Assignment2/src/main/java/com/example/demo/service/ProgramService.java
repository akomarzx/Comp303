package com.example.demo.service;

import com.example.demo.model.Program;
import com.example.demo.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/* Ronald Ombao
 * 301213219
 * November 09, 2024
 * */
@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public Program getProgramByCode(String programCode) {
        return programRepository.findByProgramCode(programCode);
    }
}
