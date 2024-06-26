package com.example.SpringdataTransactional.service;

import com.example.SpringdataTransactional.dto.OrderDto;
import com.example.SpringdataTransactional.exception.BasicException;
import com.example.SpringdataTransactional.exception.NotFoundException;
import com.example.SpringdataTransactional.model.*;
import com.example.SpringdataTransactional.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService implements ApplicationContextAware {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @Transactional
    public Order placeOrder(OrderDto orderDto) {
        OrderService self = context.getBean(OrderService.class);
        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found", HttpStatus.NOT_FOUND));
        List<Product> products = self.getProducts(orderDto.getProducts());
        double totalAmount = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
        self.changeCustomerBalance(customer, totalAmount);
        Order order = Order.builder()
                .customer(customer)
                .totalAmount(totalAmount)
                .products(products)
                .build();
        return orderRepository.save(order);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Product> getProducts(List<Long> idList) {
        return idList.stream()
                .map(id -> {
                    Product product = productRepository.findById(id)
                            .orElseThrow(() -> new NotFoundException("Product not found", HttpStatus.NOT_FOUND));
                    product.setQuantity(product.getQuantity() - 1);
                    return productRepository.save(product);
                })
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changeCustomerBalance(Customer customer, Double totalAmount) {
        if (totalAmount > customer.getBalance()) {
            throw new BasicException("Balance is too small", HttpStatus.CONFLICT);
        }
        customer.setBalance(customer.getBalance() - totalAmount);
        customerRepository.save(customer);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}

