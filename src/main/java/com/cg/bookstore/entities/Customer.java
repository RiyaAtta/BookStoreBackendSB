package com.cg.bookstore.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "customer")
@Entity
@Table(name = "customertabledb")
public class Customer {

	@ApiModelProperty(notes = "customer id")
	@Id
	@SequenceGenerator(name = "custSeqGen", sequenceName = "custSeq", initialValue = 201, allocationSize = 100)
	@GeneratedValue(generator = "custSeqGen")
	@Column(name = "cust_id")
	private int customerId;

	@ApiModelProperty(notes = "customer email")
	@Column(name = "email", unique = true)
	private String email;

	@Size(max = 40, min = 1, message = "Name invalid")
	@ApiModelProperty(notes = "Full Name")
	@Column(name = "fullname")
	private String fullName;

	@ApiModelProperty(notes = "Password")
	@Column(name = "password")
	private String password;

	@ApiModelProperty(notes = "Address")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@ApiModelProperty(notes = "Mobile Number")
	@Column(name = "mobile_number")
	private String mobileNumber;

	@ApiModelProperty(notes = "registration Date")
	@Column(name = "registration_date")
	private LocalDate registerOn;
	
	

	public Customer() {
		super();
	}

	public Customer(String email, String fullName, String password, Address address, String mobileNumber,
			LocalDate registerOn) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.password = password;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.registerOn = LocalDate.now();
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDate getRegisterOn() {
		return registerOn;
	}

	public void setRegisterOn(LocalDate registerOn) {
		this.registerOn = registerOn;
	}

}
