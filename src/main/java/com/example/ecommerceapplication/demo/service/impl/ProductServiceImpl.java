package com.example.ecommerceapplication.demo.service.impl;

import com.example.ecommerceapplication.demo.dto.ProductDTO;
import com.example.ecommerceapplication.demo.entity.ProductEntity;
import com.example.ecommerceapplication.demo.exception.ResourceNotFoundException;
import com.example.ecommerceapplication.demo.repository.ProductRepository;
import com.example.ecommerceapplication.demo.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return products.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(int productId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("CategoryEntity", "id", productId));
        return mapToDTO(product);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, int productId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("CategoryEntity", "id", productId));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setInStock(productDTO.getInStock());
        product.setSupplierId(productDTO.getSupplierId());
        //product.setCategoryId(productDTO.getCategoryId());

        ProductEntity updatedProduct = productRepository.save(product);
        return mapToDTO(updatedProduct);
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        ProductEntity product = mapToEntity(productDTO);
        ProductEntity newProduct = productRepository.save(product);

        ProductDTO productResponse = mapToDTO(newProduct);
        return productResponse;
    }

    @Override
    public void removeProduct(int productId) {
        ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("CategoryEntity", "id", productId));
        productRepository.delete(product);
    }

    private ProductDTO mapToDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(productEntity.getProductId());
        productDTO.setCategoryId(productEntity.getCategoryId().getCategoryId());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setInStock(productEntity.getInStock());
        productDTO.setSupplierId(productEntity.getSupplierId());
        productDTO.setName(productEntity.getName());
        productDTO.setDescription(productEntity.getDescription());
        return productDTO;
    }

    private ProductEntity mapToEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productDTO.getProductId());
        //productEntity.setCategoryId(productDTO.getCategoryId());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setInStock(productDTO.getInStock());
        productEntity.setSupplierId(productDTO.getSupplierId());
        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        return productEntity;
    }
}
