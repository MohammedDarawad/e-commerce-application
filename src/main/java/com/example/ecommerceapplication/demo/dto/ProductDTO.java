package com.example.ecommerceapplication.demo.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ProductDTO {
    private int productId;

    private String name;

    @Size(max = 256)
    private String description;

    private int inStock;

    private double price;

    private int supplierId;

    private int categoryid;
}
