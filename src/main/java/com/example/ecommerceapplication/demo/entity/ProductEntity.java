package com.example.ecommerceapplication.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "product"
)
public class ProductEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name="productid")
    private int productId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CategoryEntity categoryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name="instock", nullable = false)
    private int inStock;

    @Column(nullable = false)
    private double price;

    @Column(name="supplierid", nullable = false)
    private int supplierId;
}
