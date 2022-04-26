package com.example.ecommerceapplication.demo.service;

import com.example.ecommerceapplication.demo.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(int productId);

    ProductDTO addProduct(ProductDTO productDTO, int categoryId);

    void removeProduct(int productId);
}
