package com.ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "product_id")
    private Long productId;
    private String name;
    private String sku;
    private String brand;
    private int quantity;
    private BigDecimal price;
    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "productItems")
    @JsonManagedReference
    private List<Variation> variations;

    // Getters and Setters
}
