package com.example.ecommerceapplication.demo.repository;

import com.example.ecommerceapplication.demo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
}
