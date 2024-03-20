package com.example.SpringdataTransactional.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "productName")
    private String productName;
    @Column(name = "price")
    private double price;
    @Column(name = "quantity")
    private int quantity;
}
