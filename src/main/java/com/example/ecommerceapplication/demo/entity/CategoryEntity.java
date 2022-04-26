package com.example.ecommerceapplication.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "category"
)
public class CategoryEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name="categoryid")
    private int categoryId;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="category")
    private List<ProductEntity> products;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name="numofproducts", nullable = false)
    private int numOfProducts;
}
