package com.cg.bookstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="addresstabledb")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "addressorder_Sequence")
    @SequenceGenerator(name = "addressorder_Sequence", sequenceName = "ADDRESS_SEQ")
	@Column(name="address_id")
	private int addressId;
	
	@Column(name="address",nullable=false)
	private String address;
	
	@Column(name="city",nullable=false)
	private String city;
	
	@Column(name="country",nullable=false)
	private String country;
	
	@Column(name="pincode",nullable=false)
	private String pincode;

	public Address() {
		super();
	}

	public Address(String address, String city, String country, String pincode) {
		super();
		this.address = address;
		this.city = city;
		this.country = country;
		this.pincode = pincode;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}
