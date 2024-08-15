package com.ecommerce.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private ProductItem productItems;

    @OneToMany(mappedBy = "variation")
    private List<VariationOption> variationOptions;

    // Getters and Setters
}
