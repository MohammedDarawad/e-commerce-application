package com.example.ecommerceapplication.demo.repository;

import com.example.ecommerceapplication.demo.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

}
