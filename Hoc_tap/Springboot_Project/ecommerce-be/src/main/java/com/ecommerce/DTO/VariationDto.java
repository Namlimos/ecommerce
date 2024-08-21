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
    private Long productItemId;
    private String SKU;
    private BigDecimal price;
    private Integer quantity;
    private List<AttributeValueRequest> attributeValueRequests;
}
