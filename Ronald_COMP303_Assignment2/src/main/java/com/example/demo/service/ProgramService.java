package com.example.demo.service;

import com.example.demo.model.Program;
import com.example.demo.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    // Get all available programs
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    // Get program by programCode
    public Program getProgramByCode(String programCode) {
        return programRepository.findByProgramCode(programCode);
    }
}
