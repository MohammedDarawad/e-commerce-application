package com.example.ecommerceapplication.demo.controller;

import com.example.ecommerceapplication.demo.dto.CategoryDTO;
import com.example.ecommerceapplication.demo.dto.ProductDTO;
import com.example.ecommerceapplication.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/getProductById")
    public ResponseEntity<ProductDTO> getProductById(@RequestParam(name = "id") int categoryId) {
        return ResponseEntity.ok(productService.getProductById(categoryId));
    }

    @PostMapping("/{categoryId}/addProduct")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable(value = "categoryId") int categoryId) {
        return new ResponseEntity<>(productService.addProduct(productDTO, categoryId), HttpStatus.CREATED);
    }

    @DeleteMapping("/removeProduct")
    public ResponseEntity<String> deleteProduct(@RequestParam(name = "id") int categoryId) {
        productService.removeProduct(categoryId);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
