package com.example.SpringdataTransactional.controller;

import com.example.SpringdataTransactional.dto.OrderDto;
import com.example.SpringdataTransactional.model.Customer;
import com.example.SpringdataTransactional.model.Order;
import com.example.SpringdataTransactional.model.Product;
import com.example.SpringdataTransactional.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final OrderService service;

    @PostMapping("/order")
    public Order placeOrder(@RequestBody OrderDto orderDto) {
        return service.placeOrder(orderDto);
    }

    @PostMapping("/product")
    public Product createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return service.createCustomer(customer);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }

    @GetMapping("/product")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }
}
