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
    private String brand;
    private int quantity;
    private String description;
    private Long categoryId;
    private List<FileProductItemDto> files;
    private List<VariantDto> variants;

    @Data
    public static class VariantDto {
        private String sku;
        private BigDecimal price;
        private Integer stockQuantity;
        private List<AttributeValueDto> attributes;

        @Data
        public static class AttributeValueDto {
            private String attributeName;
            private String value;
        }
    }

}
