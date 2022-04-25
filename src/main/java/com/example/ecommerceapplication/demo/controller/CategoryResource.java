package com.example.ecommerceapplication.demo.controller;

import com.example.ecommerceapplication.demo.dto.CategoryDTO;
import com.example.ecommerceapplication.demo.exception.BadRequestException;
import com.example.ecommerceapplication.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @GetMapping("/getCategoryById")
    public ResponseEntity<CategoryDTO> getCategoryById(@RequestParam(name = "id") int categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @PostMapping("/createCategory")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDto) {
//        if (categoryDto != null) {
//            throw new BadRequestException(CategoryResource.class.getSimpleName(),
//                    "Id");
//        }
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDto) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryDto, categoryDto.getCategoryId()), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<String> deleteCategory(@RequestParam(name = "id") int categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
