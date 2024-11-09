package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(name="Programs", indexes = {
		@Index(name = "programs_program_code_idx", columnList = "programCode")
})
public class Program {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programId", nullable = false)
	private Long programId;
	
	@Column(name="programCode", nullable = false, unique=true)
	private String programCode;
	
	@Column(name="programName", nullable = false)
	private String programName;
	
	@Column(name="duration", nullable = false)
	private Integer durationInMonths;
	
	@Column(name="pass", nullable = false)
	private	BigDecimal fee;
	
}
