package com.example.SpringdataTransactional.repository;

import com.example.SpringdataTransactional.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
