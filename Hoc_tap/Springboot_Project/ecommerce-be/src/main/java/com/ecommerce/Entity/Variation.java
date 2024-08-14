package com.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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
    private ProductItem productItems;

    @OneToMany(mappedBy = "variation")
    private List<VariationOption> variationOptions;

    // Getters and Setters
}
