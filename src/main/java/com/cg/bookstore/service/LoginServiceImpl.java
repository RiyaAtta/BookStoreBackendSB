package com.cg.bookstore.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
 
import com.cg.bookstore.entities.Login;
import com.cg.bookstore.repository.ILoginRepository;
 
@Service
public class LoginServiceImpl implements ILoginService {
	@Autowired
	private ILoginRepository loginServiceRepo;
 
	@Override
	public ResponseEntity<String> validateLogin(String email, String password) {
		Login user = loginServiceRepo.findByEmail(email);
		if (user != null) {
			if (user.getPassword().equals(password))
				return new ResponseEntity<>("User Valid", HttpStatus.OK);
			else {
				return new ResponseEntity<>("password Invalid", HttpStatus.NOT_FOUND);
			}
		} else
			return new ResponseEntity<>("User not present", HttpStatus.NOT_FOUND);
	}
 
}