package com.example.SpringdataTransactional.repository;

import com.example.SpringdataTransactional.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
