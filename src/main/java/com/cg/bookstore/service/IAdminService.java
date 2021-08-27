package com.cg.bookstore.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Admin;


@Service
public interface IAdminService {
	
	public Admin createAdmin(Admin admin);

	ResponseEntity<String> validateLogin(String email, String password);
}