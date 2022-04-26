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

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDTO>> getAllCategories() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/getProductById")
    public ResponseEntity<ProductDTO> getCategoryById(@RequestParam(name = "id") int categoryId) {
        return ResponseEntity.ok(productService.getProductById(categoryId));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductDTO> createCategory(@Valid @RequestBody ProductDTO productDTO) {
//        if (categoryDto != null) {
//            throw new BadRequestException(CategoryResource.class.getSimpleName(),
//                    "Id");
//        }
        return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<ProductDTO> updateCategory(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.updateProduct(productDTO, productDTO.getProductId()), HttpStatus.OK);
    }

    @DeleteMapping("/removeProduct")
    public ResponseEntity<String> deleteCategory(@RequestParam(name = "id") int categoryId) {
        productService.removeProduct(categoryId);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
