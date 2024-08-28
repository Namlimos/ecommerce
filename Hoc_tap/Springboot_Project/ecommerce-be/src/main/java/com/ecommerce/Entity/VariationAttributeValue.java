package com.ecommerce.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "variation_attribute_value")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationAttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long variationId;

    private Long attributeValueId;

}
