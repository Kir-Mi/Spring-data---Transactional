package com.example.SpringdataTransactional.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
@Data
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @ManyToOne
    private Customer customer;
    @Column(name = "total_amount")
    private double totalAmount;
    @Transient
    private List<Product> products;
}
