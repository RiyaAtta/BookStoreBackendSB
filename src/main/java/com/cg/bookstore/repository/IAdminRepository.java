package com.cg.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Admin;


@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer>{
	
	public Admin findByEmail(String email);
	
	

}