package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
/* Ronald Ombao
 * 301213219
 * November 09, 2024
 * */
@Entity
@Table(name="Enrollment")
public class Enrollment {
	
	public Enrollment() {
		super();
	}

	public Enrollment(Long applicationNo, LocalDate startDate, BigDecimal amountPaid, String status, Student student,
			Program program) {
		super();
		this.applicationNo = applicationNo;
		this.startDate = startDate;
		this.amountPaid = amountPaid;
		this.status = status;
		this.student = student;
		this.program = program;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicationNo", nullable = false)
	private Long applicationNo;
	
	public Long getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(Long applicationNo) {
		this.applicationNo = applicationNo;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="startDate", nullable = false)
	private LocalDate startDate;
	
	@Column(name="amountPaid", nullable = false)
	private BigDecimal amountPaid;
	
	@Column(name="status", nullable = false)
	private String status;
	
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "programCode", nullable = false)
    private Program program;
    
}
