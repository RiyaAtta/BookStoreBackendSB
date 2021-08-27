package com.cg.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long>{
	@Query(nativeQuery=true,value="select o.* from orderstabledb o"
			+ " where o.customeremail = :email")
	List<Order> findByCustomerEmail(String email);

}
