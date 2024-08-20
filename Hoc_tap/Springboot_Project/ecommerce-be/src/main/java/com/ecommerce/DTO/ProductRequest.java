package com.ecommerce.DTO;

import com.ecommerce.Entity.FileApp;
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
    private String sku;
    private String brand;
    private String description;
    private Long categoryId;
    private List<VariationDto> variations;
}
