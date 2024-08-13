package com.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "variation_option")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String optionName;

    private String optionValue;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "variation_id")
    private Variation variation;

}
