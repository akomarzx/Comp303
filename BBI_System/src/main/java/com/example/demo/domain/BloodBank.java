package com.example.demo.domain;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
@Entity
@Table(name = "BloodBank",indexes = {
	       @Index(name = "idx_patient_user_id_a", columnList = "userId", unique = true)
})
public class BloodBank {
	
	public BloodBank() {
		super();
	}

	public BloodBank(Long bloodBankId, User user, String bloodBankName, String address, String city, String phone,
			String website, String email, List<BloodStock> bloodStocks) {
		super();
		this.bloodBankId = bloodBankId;
		this.user = user;
		this.bloodBankName = bloodBankName;
		this.address = address;
		this.city = city;
		this.phone = phone;
		this.website = website;
		this.email = email;
		this.bloodStocks = bloodStocks;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bloodBankId", nullable = false)
	private Long bloodBankId;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
    
    @Column(name = "bloodBankName", nullable = false, unique = true)
    private String bloodBankName;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "website")
    private String website;
    
    @Column(name = "email")
    private String email;
    
    @OneToMany(mappedBy = "bloodStockId", fetch = FetchType.LAZY)
    private List<BloodStock> bloodStocks;

	public Long getBloodBankId() {
		return bloodBankId;
	}

	public void setBloodBankId(Long bloodBankId) {
		this.bloodBankId = bloodBankId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBloodBankName() {
		return bloodBankName;
	}

	public void setBloodBankName(String bloodBankName) {
		this.bloodBankName = bloodBankName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<BloodStock> getBloodStocks() {
		return bloodStocks;
	}

	public void setBloodStocks(List<BloodStock> bloodStocks) {
		this.bloodStocks = bloodStocks;
	}
}
