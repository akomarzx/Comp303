/**
 * 
 */
package com.example.demo.domain;
/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Ronald Jr Ombao
 * 301213219
 * November 14, 2024
 */
@Entity
@Table(name = "Patient", indexes = {
       @Index(name = "idx_patient_user_id_a", columnList = "userId", unique = true)
})
public class Patient {
	
	public Patient() {
		super();
	}

	public Patient(Long patientId, User user, String firstName, String lastName, String gender, BloodGroup bloodGroup,
			String phone, String city) {
		super();
		this.patientId = patientId;
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.phone = phone;
		this.city = city;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patientId", nullable = false)
	private Long patientId;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@Column(name = "fistName", nullable = false)
	private String firstName;
	
	@Column(name = "lastName", nullable = false)
	private String lastName;
	
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "bloodGroup", nullable = false)
	private BloodGroup bloodGroup;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "city", nullable = false)
	private String city;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
