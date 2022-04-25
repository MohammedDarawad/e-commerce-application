package com.example.ecommerceapplication.demo.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CategoryDTO {
    private int categoryId;

    @Size(max = 64)
    private String name;

    @Size(max = 256)
    private String description;

    private int numOfProducts;
}
