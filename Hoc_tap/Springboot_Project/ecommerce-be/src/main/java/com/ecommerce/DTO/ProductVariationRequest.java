package com.ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductVariationRequest {
    private Long productId;
    private List<VariantDto> variants;

    @Data
    public static class VariantDto {
        private String sku;
        private BigDecimal price;
        private Integer stockQuantity;
        private List<AttributeDto> attributes;

        @Data
        public static class AttributeDto {
            private Long attributeId;
            private String value;
        }
    }
}
