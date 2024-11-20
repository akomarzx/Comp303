package com.example.demo.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
@Entity
@Table(name = "BloodStock" , indexes = {
		@Index(name = "idx_blood_stock_blood_bank_id_a", columnList = "bloodBankId"),
		@Index(name = "idx_blood_stock_serial_no_a", columnList = "serialNo"),
		
})
public class BloodStock {
	
	public BloodStock() {
		super();
	}

	public BloodStock(Long bloodStockId, Long quantity, LocalDateTime bestBeforeDate, BloodGroup bloodGroup,
			BloodStockStatus status, BloodBank bloodBank, String serialNo) {
		super();
		this.bloodStockId = bloodStockId;
		this.quantity = quantity;
		this.bestBeforeDate = bestBeforeDate;
		this.bloodGroup = bloodGroup;
		this.status = status;
		this.bloodBank = bloodBank;
		this.serialNo = serialNo;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bloodStockId", nullable = false)
	private Long bloodStockId;
	
	@Column(name = "quantity", nullable = false)
	private Long quantity;
	
	@Column(name = "bestBefore", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime bestBeforeDate;
	
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

	public Long getBloodStockId() {
		return bloodStockId;
	}

	public void setBloodStockId(Long bloodStockId) {
		this.bloodStockId = bloodStockId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getBestBeforeDate() {
		return bestBeforeDate;
	}

	public void setBestBeforeDate(LocalDateTime bestBeforeDate) {
		this.bestBeforeDate = bestBeforeDate;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public BloodStockStatus getStatus() {
		return status;
	}

	public void setStatus(BloodStockStatus status) {
		this.status = status;
	}

	public BloodBank getBloodBank() {
		return bloodBank;
	}

	public void setBloodBank(BloodBank bloodBank) {
		this.bloodBank = bloodBank;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
    
    
}
