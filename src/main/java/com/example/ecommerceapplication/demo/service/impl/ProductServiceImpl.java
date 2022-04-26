package com.example.ecommerceapplication.demo.service.impl;

import com.example.ecommerceapplication.demo.dto.ProductDTO;
import com.example.ecommerceapplication.demo.entity.CategoryEntity;
import com.example.ecommerceapplication.demo.entity.ProductEntity;
import com.example.ecommerceapplication.demo.exception.ResourceNotFoundException;
import com.example.ecommerceapplication.demo.repository.CategoryRepository;
import com.example.ecommerceapplication.demo.repository.ProductRepository;
import com.example.ecommerceapplication.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private ModelMapper mapper;


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
    public ProductDTO addProduct(ProductDTO productDTO, int categoryId) {
        productDTO.setCategoryid(categoryId);
        System.out.println(productDTO);
        ProductEntity product = mapToEntity(productDTO);

        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("category", "id", categoryId));

        product.setCategory(category);
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
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setDescription(productEntity.getDescription());
        productDTO.setName(productEntity.getName());
        productDTO.setSupplierId(productEntity.getSupplierId());
        productDTO.setInStock(productEntity.getInStock());
        productDTO.setCategoryid(productEntity.getCategory().getCategoryId());
        return productDTO;
    }

    private ProductEntity mapToEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productDTO.getProductId());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setName(productDTO.getName());
        productEntity.setSupplierId(productDTO.getSupplierId());
        productEntity.setInStock(productDTO.getInStock());
        return productEntity;
    }
}
