package com.ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationOptionRequest {
    private String optionValue;
    private Integer quantity;
    private BigDecimal price;
    private Long variationId;


}
