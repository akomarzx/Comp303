package com.comp_303.Assignment1.Model;


public class OrderModel {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String emailAddress;
	private String phoneAreaCode;
	private String phoneNumber;
	private String streetAddressLine1;
	private String streetAddressLine2;
	private String city;
	private String province;
	private String postalCode;
	private String country;
	
	public OrderModel() {
		super();
	}

	public OrderModel(String firstName, String middleName, String lastName, String emailAddress, String phoneAreaCode,
			String phoneNumber, String streetAddressLine1, String streetAddressLine2, String city, String province,
			String postalCode, String country) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneAreaCode = phoneAreaCode;
		this.phoneNumber = phoneNumber;
		this.streetAddressLine1 = streetAddressLine1;
		this.streetAddressLine2 = streetAddressLine2;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
		this.country = country;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneAreaCode() {
		return phoneAreaCode;
	}

	public void setPhoneAreaCode(String phoneAreaCode) {
		this.phoneAreaCode = phoneAreaCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreetAddressLine1() {
		return streetAddressLine1;
	}

	public void setStreetAddressLine1(String streetAddressLine1) {
		this.streetAddressLine1 = streetAddressLine1;
	}

	public String getStreetAddressLine2() {
		return streetAddressLine2;
	}

	public void setStreetAddressLine2(String streetAddressLine2) {
		this.streetAddressLine2 = streetAddressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
