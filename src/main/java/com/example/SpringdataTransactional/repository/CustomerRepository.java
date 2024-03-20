package com.example.SpringdataTransactional.repository;

import com.example.SpringdataTransactional.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
