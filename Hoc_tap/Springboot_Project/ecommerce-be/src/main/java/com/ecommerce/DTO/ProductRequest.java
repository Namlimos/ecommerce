package com.ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String Sku;
    private String brand;
    private int quantity;
    private BigDecimal price;
    private String description;
    private String category;
    private Long draftProductId;
    private List<VariationRequest> variations;
}
