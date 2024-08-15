package com.ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String name;
    private String sku;
    private String brand;
    private int quantity;
    private BigDecimal price;
    private String description;
    private Long categoryId;
    private Long draftProductId;
    private List<VariationDto> variations;
}
