package com.cg.bookstore.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Admin;
import com.cg.bookstore.exceptions.CustomException;
import com.cg.bookstore.repository.IAdminRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private IAdminRepository adminRepository;

	@Override
	public Admin createAdmin(@Valid Admin admin) {
		if(!admin.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
			throw new CustomException("Email invalid!");
		}
		if(!admin.getPassword().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
			throw new CustomException("Password Invalid");
		}
		Admin findAdminByEmail = adminRepository.findByEmail(admin.getEmail());
		if (findAdminByEmail == null) {
			return adminRepository.save(admin);
		} else {
			throw new CustomException("Admin " + admin.getEmail() + " already exists!!");
		}
	}

	@Override
	public ResponseEntity<String> validateLogin(String email, String password) {
		Admin admin = adminRepository.findByEmail(email);
		if (admin != null) {
			if (admin.getPassword().equals(password))
				return new ResponseEntity<>("User Valid", HttpStatus.OK);
			else {
				return new ResponseEntity<>("password Invalid", HttpStatus.NOT_FOUND);
			}
		} else
			return new ResponseEntity<>("User not present", HttpStatus.NOT_FOUND);
	}
}
