package com.cg.bookstore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "logintabledb")
@ApiModel(description = "Login")

public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_Sequence")
	@Column(name = "loginid")
	@ApiModelProperty(notes = "Id of Customer")
	private int loginId;

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	@Email
	@Column(name = "email")
	@ApiModelProperty(notes = "Email of the Customer")
	private String email;

	@Column(name = "password")
	@ApiModelProperty(notes = "Password of the Customer")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Login() {
		super();
	}

	public Login(int loginId, String email, String password) {
		super();
		this.loginId = loginId;
		this.email = email;
		this.password = password;
	}

}