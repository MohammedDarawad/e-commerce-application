package com.example.ecommerceapplication.demo.service;

import com.example.ecommerceapplication.demo.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDto);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(int categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDto, int categoryId);

    void deleteCategoryById(int categoryId);
}
