package com.cg.bookstore.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Login;

@Repository
public interface ILoginRepository extends JpaRepository<Login, Integer>{
	public Login findByEmail(String email);
	public Login findByPassword(String password);
	public Optional<Login> findByEmailAndPassword(@Param(value = "email") String email,@Param(value = "password") String password);
	

}