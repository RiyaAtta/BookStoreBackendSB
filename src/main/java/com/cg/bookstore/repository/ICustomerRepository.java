package com.cg.bookstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Login;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Integer>{

	Optional<Customer> findByEmail(String email);

	Customer getByEmail(String email);



}
