package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
/* Ronald Ombao
 * 301213219
 * November 09, 2024
 * */
@Entity
@Table(name="Programs", indexes = {
		@Index(name = "programs_program_code_idx", columnList = "programCode")
})
public class Program {
	
	public Program(Long programId, String programCode, String programName, Integer durationInMonths, BigDecimal fee) {
		super();
		this.programId = programId;
		this.programCode = programCode;
		this.programName = programName;
		this.durationInMonths = durationInMonths;
		this.fee = fee;
	}

	public Program() {
		super();
	}

	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public Integer getDurationInMonths() {
		return durationInMonths;
	}

	public void setDurationInMonths(Integer durationInMonths) {
		this.durationInMonths = durationInMonths;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Program(String programCode, String programName, Integer durationInMonths, BigDecimal fee) {
		super();
		this.programCode = programCode;
		this.programName = programName;
		this.durationInMonths = durationInMonths;
		this.fee = fee;
	}

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
