package com.example.ecommerceapplication.demo.repository;

import com.example.ecommerceapplication.demo.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
