package com.example.demo.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="Enrollment")
public class Enrollment {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicationNo", nullable = false)
	private Long applicationNo;
	
	@Column(name="studentId", nullable = false)
	private Long studentId;
	
	@Column(name="programCode", nullable = false)
	private String programCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name="startDate", nullable = false)
	private LocalDate startDate;
	
	@Column(name="amountPaid", nullable = false)
	private BigDecimal amountPaid;
	
	@Column(name="status", nullable = false)
	private String status;
	
}
