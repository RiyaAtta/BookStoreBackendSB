package com.cg.bookstore.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface ILoginService {

	public ResponseEntity<String> validateLogin(String email, String password);
}