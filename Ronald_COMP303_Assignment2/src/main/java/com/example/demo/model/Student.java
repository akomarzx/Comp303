package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/* Ronald Ombao
 * 301213219
 * November 09, 2024
 * */
@Entity
@Table(name="Students")
public class Student {
	
	public Student() {
		super();
	}

	public Student(Long studentId, String username, String password, String firstName, String lastName, String address,
			String postalCode) {
		super();
		this.id = studentId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.postalCode = postalCode;
	}

	public Long getStudentId() {
		return id;
	}

	public void setStudentId(Long studentId) {
		this.id = studentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name="username", nullable = false)
	private String username;
	
	@Column(name="password", nullable = false)
	private String password;
	
	@Column(name="firstName", nullable = false)
	private String firstName;
	
	@Column(name="lastName", nullable = false)
	private String lastName;
	
	@Column(name="address", nullable = false)
	private String address;
	
	@Column(name="postalCode", nullable = false)
	private String postalCode;
	
}
