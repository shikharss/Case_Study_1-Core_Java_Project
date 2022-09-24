package com.employee.model;

import java.time.LocalDate;
import java.util.Objects;

/*
 * Primary Key -> email 
 */
public class Employee implements Comparable<Employee>{

	private String firstName; 
	private String lastName;
	private String address;
	private String email;
	private long phoneNumber;
	private LocalDate dateOfBirth; 
	private LocalDate dateOfWedding;

	public Employee() {
		super();
	}

	public Employee(String firstName, String lastName, String address, String email, long phoneNumber,
			LocalDate dateOfBirth, LocalDate dateOfWedding) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.dateOfWedding = dateOfWedding;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateOfWedding() {
		return dateOfWedding;
	}

	public void setDateOfWedding(LocalDate dateOfWedding) {
		this.dateOfWedding = dateOfWedding;
	}

	/*
	 * Default Sort is done by dateOfBirth
	 */
	@Override
	public int compareTo(Employee empObj) {
		return this.dateOfBirth.compareTo(empObj.dateOfBirth) ; 
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(email, other.email);
	}
	
	@Override
	public String toString() {
		return "FirstName = " + firstName + ", LastName = " + lastName + ", Address = " + address + ", Email = "
				+ email + ", PhoneNumber = " + phoneNumber + ", DateOfBirth = " + dateOfBirth + ", DateOfWedding = "
				+ dateOfWedding ;
	}
}
