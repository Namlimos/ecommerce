package com.ecommerce.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationOptionRequest {
    private String optionName;
    private String optionValue;
    private Integer quantity;
    private String image;
}
