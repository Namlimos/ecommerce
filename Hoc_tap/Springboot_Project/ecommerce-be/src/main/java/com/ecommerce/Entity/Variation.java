package com.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "variation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Variation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String variationName;

    @OneToMany(mappedBy = "variation")
    private List<ProductItem> productItems;

    @OneToMany(mappedBy = "variation")
    private List<VariationOption> variationOptions;

    // Getters and Setters
}
