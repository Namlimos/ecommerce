package com.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "prodcut_variation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_item_id")
    private Long productItemId;

    private String sku;
    private BigDecimal price;
    private Integer quantity;

}
