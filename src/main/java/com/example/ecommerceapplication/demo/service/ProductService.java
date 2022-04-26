package com.example.ecommerceapplication.demo.service;

import com.example.ecommerceapplication.demo.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    ProductDTO getProductById(int productId);

    ProductDTO updateProduct(ProductDTO productDTO, int productId);

    ProductDTO addProduct(ProductDTO productDTO);

    void removeProduct(int productId);
}
