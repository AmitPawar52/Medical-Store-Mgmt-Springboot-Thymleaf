package com.store.medical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.store.medical.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select max(id) from Customer")
	int getMaxId();

	@Query("SELECT c FROM Customer c WHERE CONCAT(c.name, ' ', c.address, ' ', c.mob_num, ' ', c.dob) LIKE %?1%")
	List<Customer> searchCustomer(String cust_name);
}
