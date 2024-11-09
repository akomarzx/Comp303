package com.example.demo.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Program;
import com.example.demo.repository.ProgramRepository;

import jakarta.transaction.Transactional;


/* Ronald Ombao
 * 301213219
 * November 09, 2024
 * */
@Component
public class BootstrapDatabase implements CommandLineRunner {
	
	private final ProgramRepository programRepository;
	
	public BootstrapDatabase(ProgramRepository repository) {
		this.programRepository = repository;
	}
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Program p1 = new Program("COMP-303", "Enterprise App Development", 4, new BigDecimal(300.0));
		Program p2 = new Program("COMP-216", "Networking for Software Developers", 4, new BigDecimal(300.0));
		Program p3 = new Program("COMP-231", "Software Development Project", 4, new BigDecimal(400.0));
		Program p4 = new Program("COMP-306", "API Engineering and Cloud Computing", 4, new BigDecimal(200.0));
		
		this.programRepository.saveAll(List.of(p1, p2, p3, p4));
		
	}

}
