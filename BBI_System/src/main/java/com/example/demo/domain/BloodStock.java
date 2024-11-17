package com.example.demo.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "BloodStock" , indexes = {
		@Index(name = "idx_blood_stock_blood_bank_id_a", columnList = "bloodBankId"),
		@Index(name = "idx_blood_stock_serial_no_a", columnList = "serialNo"),
		
})
public class BloodStock {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bloodStockId", nullable = false)
	private Long bloodStockId;
	
	@Column(name = "quantity", nullable = false)
	private Long quantity;
	
	@Column(name = "bestBefore", nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate bestBeforeDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "bloodGroup")
	private BloodGroup bloodGroup;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private BloodStockStatus status;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bloodBankId")
    private BloodBank bloodBank;
	
    @Column(name = "serialNo", nullable = false, unique = true)
    private String serialNo;
    
    
}
