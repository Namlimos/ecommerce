package com.ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationDto {
    private String SKU;
    private BigDecimal price;
    private Integer quantity;
    private Long productItemId;
    private List<VariationOptionRequest> options;
}
