package com.example.ecommerceapplication.demo.service.impl;

import com.example.ecommerceapplication.demo.dto.CategoryDTO;
import com.example.ecommerceapplication.demo.entity.CategoryEntity;
import com.example.ecommerceapplication.demo.exception.ResourceNotFoundException;
import com.example.ecommerceapplication.demo.repository.CategoryRepository;
import com.example.ecommerceapplication.demo.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO CategoryDTO) {
        CategoryEntity category = mapToEntity(CategoryDTO);
        CategoryEntity newCategory = categoryRepository.save(category);

        CategoryDTO categoryResponse = mapToDTO(newCategory);
        return categoryResponse;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories.stream().map(category -> mapToDTO(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(int categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("CategoryEntity", "id", categoryId));
        return mapToDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, int categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("CategoryEntity", "id", categoryId));

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        CategoryEntity updatedCategory = categoryRepository.save(category);
        return mapToDTO(updatedCategory);
    }

    @Override
    public void deleteCategoryById(int categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("CategoryEntity", "id", categoryId));
        categoryRepository.delete(category);
    }

    private CategoryDTO mapToDTO(CategoryEntity category) {
        CategoryDTO CategoryDTO = new CategoryDTO();
        CategoryDTO.setCategoryId(category.getCategoryId());
        CategoryDTO.setName(category.getName());
        CategoryDTO.setDescription(category.getDescription());
        CategoryDTO.setNumOfProducts(category.getNumOfProducts());
        return CategoryDTO;
    }

    private CategoryEntity mapToEntity(CategoryDTO CategoryDTO) {
        CategoryEntity category = new CategoryEntity();
        category.setName(CategoryDTO.getName());
        category.setDescription(CategoryDTO.getDescription());
        category.setNumOfProducts(CategoryDTO.getNumOfProducts());
        return category;
    }
}
